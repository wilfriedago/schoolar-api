package dev.thewlabs.schoolar.domain.classrooms; // Adjust the package name accordingly

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.events.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classrooms")
@EqualsAndHashCode(callSuper = false)
public class Classroom extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false) // New property for classroom capacity
    private int capacity;

    @OneToMany(mappedBy = "classroom")
    private List<Event> events;
}
