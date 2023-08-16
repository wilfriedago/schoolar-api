package dev.thewlabs.schoolar.domain.subjects;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.courses.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
@EqualsAndHashCode(callSuper = false)
public class Subject extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<Course> courses;
}
