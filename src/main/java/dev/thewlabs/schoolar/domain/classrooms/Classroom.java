package dev.thewlabs.schoolar.domain.classrooms; // Adjust the package name accordingly

import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
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
@Table(name = "classrooms")
@EqualsAndHashCode(callSuper = false)
public class Classroom extends Traceable implements HasTimetables<ClassroomTimetable> { // You don't see any error because @lombok.Data already create the getTimetables method !
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "classroom")
    private List<Event> events;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom")
    private List<ClassroomTimetable> timetables;

    @Override
    public Optional<ClassroomTimetable> findTimeslotTimetable(Timeslot timeslot) {
        return TimetableUtils.findTimeslotTimetable(timeslot, this);
    }
}
