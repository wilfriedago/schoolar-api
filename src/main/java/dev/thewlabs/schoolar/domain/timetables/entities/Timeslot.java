package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.events.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MAX_DURATION;
import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MIN_DURATION;

@Data
@Entity
@NoArgsConstructor
@Table(name = "timeslots", uniqueConstraints = @UniqueConstraint(name = "uk_timeslot", columnNames = {"start_time", "end_time", "classroom_timetable_id", "group_timetable_id"}))
public class Timeslot {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, name = "start_time")
    private ZonedDateTime startTime;

    @Check(name = "ck_end_time", constraints = "end_time > start_time")
    @Column(nullable = false, name = "end_time")
    private ZonedDateTime endTime;

    @OneToOne(mappedBy = "timeslot", optional = false)
    private Event event;

    @JoinColumn(name = "group_timetable_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private GroupTimetable groupTimetable;

    @JoinColumn(name = "classroom_timetable_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ClassroomTimetable classroomTimetable;

    public Timeslot(ZonedDateTime startTime, ZonedDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timeslot timeslot)) return false;
        return Objects.equals(getStartTime(), timeslot.getStartTime()) && Objects.equals(getEndTime(), timeslot.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getEndTime());
    }

    public boolean isStartTimeFutureOrPresent() {
        return startTime.isAfter(ZonedDateTime.now()) || startTime.equals(ZonedDateTime.now());
    }

    public boolean isEndTimeFuture() {
        return endTime.isAfter(ZonedDateTime.now());
    }

    public boolean isStartDifferentFromEnd() {
        return !startTime.equals(endTime);
    }

    public boolean isStartBeforeEnd() {
        return startTime.isBefore(endTime);
    }

    public boolean isStartAndEndSameDay() {
        return startTime.toLocalDate().equals(endTime.toLocalDate());
    }

    public boolean isDurationTooShort() {
        Duration eventDuration = Duration.between(startTime, endTime);
        return eventDuration.compareTo(EVENT_MIN_DURATION) < 0;
    }

    public boolean isDurationTooLong() {
        Duration eventDuration = Duration.between(startTime, endTime);
        return eventDuration.compareTo(EVENT_MAX_DURATION) >= 0;
    }
}
