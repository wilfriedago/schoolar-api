package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.events.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MIN_DURATION;

@Data
@Entity
@NoArgsConstructor
@Table(name = "timeslots")
public class Timeslot {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    @FutureOrPresent
    private ZonedDateTime startTime;
    @Check(name = "ck_end_time", constraints = "end_time > start_time")
    @Column(nullable = false)
    @Future
    private ZonedDateTime endTime;
    @OneToOne(mappedBy = "timeslot", optional = false)
    private Event event;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_timetable_id")
    private GroupTimetable groupTimetable;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_timetable_id")
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

    public Boolean isStartEqualsEnd() {
        return startTime.equals(endTime);
    }

    public Boolean isEndBeforeStart() {
        return endTime.isBefore(startTime);
    }

    public Boolean isDurationValid() {
        return Duration.between(startTime, endTime).compareTo(EVENT_MIN_DURATION) < 0;
    }

    public Boolean isValid() {
        return !isStartEqualsEnd() && !isEndBeforeStart() && isDurationValid();
    }
}
