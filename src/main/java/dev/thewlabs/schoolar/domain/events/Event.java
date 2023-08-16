package dev.thewlabs.schoolar.domain.events;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.events.enums.EventColor;
import dev.thewlabs.schoolar.domain.events.enums.EventStatus;
import dev.thewlabs.schoolar.domain.events.enums.EventType;
import dev.thewlabs.schoolar.domain.students.Student;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Check;

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

    private boolean allDay;

    @Column(nullable = false)
    private ZonedDateTime startTime;

    @Check(name = "ck_end", constraints = "end_time > start_time")
    @Column(nullable = false)
    private ZonedDateTime endTime;

    @Transient
    private EventType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status = EventStatus.DRAFT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventColor color = EventColor.BLUE;

    @JoinColumn(name = "classroom_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Classroom classroom;

    @JoinColumn(name = "organizer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User organizer;

    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> attendees;
}
