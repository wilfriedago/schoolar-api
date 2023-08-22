package dev.thewlabs.schoolar.domain.courses;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.assessments.Assessment;
import dev.thewlabs.schoolar.domain.groups.Group;
import dev.thewlabs.schoolar.domain.sessions.Session;
import dev.thewlabs.schoolar.domain.subjects.Subject;
import dev.thewlabs.schoolar.domain.teachers.Teacher;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Check;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "courses")
@EqualsAndHashCode(callSuper = false)
public class Course extends Traceable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @Check(name = "ck_hours", constraints = "hours >= 0")
    @Column(nullable = false)
    private Integer hours;

    @Check(name = "ck_hours_done", constraints = "hours >= hours_done")
    private Integer hoursDone;

    private Integer hoursLeft;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private List<Session> sessions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Assessment> assessments;

    public @NotNull List<User> getAttendees() {
        List<? extends User> courseStudents = this.group.getStudents();
        List<? extends User> courseTeachers = this.teachers;

        List<User> attendees = new ArrayList<>();
        attendees.addAll(courseStudents);
        attendees.addAll(courseTeachers);

        return attendees;
    }
}
