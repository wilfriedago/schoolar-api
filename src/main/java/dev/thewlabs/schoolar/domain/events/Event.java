package dev.thewlabs.schoolar.domain.events;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "events")
@EqualsAndHashCode(callSuper = false)
public abstract class Event extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private String url;

    @Column(nullable = false)
    private Boolean allDay = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.DRAFT;

    @Transient
    private EventType type;

    @JoinColumn(name = "timeslot_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Timeslot timeslot;

    @JoinColumn(name = "classroom_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Classroom classroom;

    @JoinColumn(name = "organizer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User organizer;

    @JoinTable(
            name = "attendances",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> attendees;

    public abstract EventType getType();

    @PostLoad
    void updateType() {
        setType(getType());
    }

    public ZonedDateTime getStart() {
        return timeslot.getStartTime();
    }

    public ZonedDateTime getEnd() {
        return timeslot.getEndTime();
    }
}
