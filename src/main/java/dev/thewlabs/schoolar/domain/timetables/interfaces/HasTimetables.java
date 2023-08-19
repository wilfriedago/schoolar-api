package dev.thewlabs.schoolar.domain.timetables.interfaces;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;

import java.util.List;
import java.util.Optional;

public interface HasTimetables<T> {
    List<T> getTimetables();

    Optional<T> findTimeslotTimetable(Timeslot timeslot);
}

