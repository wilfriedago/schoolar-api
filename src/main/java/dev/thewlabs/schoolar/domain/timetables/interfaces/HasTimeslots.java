package dev.thewlabs.schoolar.domain.timetables.interfaces;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;

import java.util.List;

public interface HasTimeslots {
    List<Timeslot> getTimeslots();

    Boolean isTimeslotBooked(Timeslot timeslot);
}
