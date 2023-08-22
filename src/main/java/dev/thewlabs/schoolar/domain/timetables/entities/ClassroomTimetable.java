package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.timetables.enums.TimetableType;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimeslots;
import dev.thewlabs.schoolar.domain.timetables.utils.TimeslotUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "classroom_timetables", uniqueConstraints = @UniqueConstraint(name = "uk_timetable", columnNames = {"start_date", "end_date", "classroom_id"}))
@EqualsAndHashCode(callSuper = false)
public class ClassroomTimetable extends Timetable implements HasTimeslots {
    @JoinColumn(name = "classroom_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Classroom classroom;
    @OneToMany(mappedBy = "classroomTimetable", fetch = FetchType.EAGER)
    private List<Timeslot> timeslots;

    public ClassroomTimetable() {
        super();
    }

    public ClassroomTimetable(Timeslot timeslot) {
        super(timeslot);
    }

    @PostLoad
    void setup() {
        setType(TimetableType.CLASSROOM_TIMETABLE);
    }

    @Override
    public Boolean isTimeslotFree(Timeslot timeslot) {
        return TimeslotUtils.isTimeslotFree(timeslot, this);
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();

        for (Timeslot timeslot : timeslots) events.add(timeslot.getEvent());

        return events;
    }
}
