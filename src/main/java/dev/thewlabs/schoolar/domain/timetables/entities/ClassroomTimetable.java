package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.timetables.enums.TimetableType;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimeslots;
import dev.thewlabs.schoolar.domain.timetables.utils.TimeslotUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "classroom_timetables")
@EqualsAndHashCode(callSuper = false)
public class ClassroomTimetable extends Timetable implements HasTimeslots {
    @JoinColumn(name = "classroom_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Classroom classroom;
    @OneToMany(mappedBy = "classroomTimetable", fetch = FetchType.EAGER)
    private List<Timeslot> timeslots;

    @PostLoad
    void setup() {
        setType(TimetableType.CLASSROOM_TIMETABLE);
    }

    @Override
    public Boolean isTimeslotBooked(Timeslot timeslot) {
        return TimeslotUtils.isTimeslotBooked(timeslot, this);
    }
}
