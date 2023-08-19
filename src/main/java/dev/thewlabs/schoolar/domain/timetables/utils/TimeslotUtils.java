package dev.thewlabs.schoolar.domain.timetables.utils;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimeslots;
import org.jetbrains.annotations.NotNull;

public class TimeslotUtils {
    private TimeslotUtils() {
    }

    public static @NotNull Boolean isTimeslotBooked(@NotNull Timeslot timeslot, @NotNull HasTimeslots entity) {
        return entity.getTimeslots().contains(timeslot);
    }
}
