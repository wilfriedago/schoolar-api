package dev.thewlabs.schoolar.domain.timetables.entities;

import dev.thewlabs.schoolar.domain.events.Event;
import dev.thewlabs.schoolar.domain.groups.Group;
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
@Table(name = "group_timetables", uniqueConstraints = @UniqueConstraint(name = "uk_timetable", columnNames = {"start_date", "end_date", "group_id"}))
@EqualsAndHashCode(callSuper = false)
public class GroupTimetable extends Timetable implements HasTimeslots {
    @OneToMany(mappedBy = "groupTimetable", fetch = FetchType.EAGER)
    List<Timeslot> timeslots;
    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    public GroupTimetable() {
        super();
    }

    public GroupTimetable(Timeslot timeslot) {
        super(timeslot);
    }

    @PostLoad
    void setup() {
        setType(TimetableType.GROUP_TIMETABLE);
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
