package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.groups.Group;
import dev.thewlabs.schoolar.domain.timetables.enums.TimetableType;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimeslots;
import dev.thewlabs.schoolar.domain.timetables.utils.TimeslotUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "group_timetables")
@EqualsAndHashCode(callSuper = false)
public class GroupTimetable extends Timetable implements HasTimeslots {
    @OneToMany(mappedBy = "groupTimetable", fetch = FetchType.EAGER)
    List<Timeslot> timeslots;
    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @PostLoad
    void setup() {
        setType(TimetableType.GROUP_TIMETABLE);
    }

    @Override
    public Boolean isTimeslotBooked(Timeslot timeslot) {
        return TimeslotUtils.isTimeslotBooked(timeslot, this);
    }
}
