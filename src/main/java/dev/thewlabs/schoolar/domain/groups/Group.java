package dev.thewlabs.schoolar.domain.groups;

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.students.Student;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimetables;
import dev.thewlabs.schoolar.domain.timetables.utils.TimetableUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Entity
@Table(name = "groups")
@EqualsAndHashCode(callSuper = false)
public class Group extends Traceable implements HasTimetables<GroupTimetable> {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<Course> courses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private List<GroupTimetable> timetables;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_students",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private List<Student> students;

    @Override
    public Optional<GroupTimetable> findTimeslotTimetable(Timeslot timeslot) {
        return TimetableUtils.findTimeslotTimetable(timeslot, this);
    }
}
