package dev.thewlabs.schoolar.domain.attendances;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "attendances") // Make sure the table name matches your existing join table name
@EqualsAndHashCode(callSuper = false)
public class Attendance extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    @Column(name = "is_present")
    private boolean isPresent;

    @Column(name = "observation", length = 1000)
    private String observation;
}

