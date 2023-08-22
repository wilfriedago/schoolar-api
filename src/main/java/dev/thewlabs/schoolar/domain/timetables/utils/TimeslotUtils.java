package dev.thewlabs.schoolar.domain.timetables.utils;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimeslots;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class TimeslotUtils {
    private TimeslotUtils() {
    }

    public static @NotNull Boolean isTimeslotFree(@NotNull Timeslot timeslot, @NotNull HasTimeslots entity) {
        for (Timeslot entityTimeslot : entity.getTimeslots()) // For each timeslot in the entity
            if (doTimeslotsOverlap(timeslot, entityTimeslot))
                return false; // Overlapping timeslots found, it's not free.

        return true; // No overlapping timeslots found, it's free.
    }

    // Helper method to check if two timeslots overlap.
    private static boolean doTimeslotsOverlap(@NotNull Timeslot timeslot1, @NotNull Timeslot timeslot2) {
        LocalDateTime start1 = timeslot1.getStartTime().toLocalDateTime();
        LocalDateTime end1 = timeslot1.getEndTime().toLocalDateTime();
        LocalDateTime start2 = timeslot2.getStartTime().toLocalDateTime();
        LocalDateTime end2 = timeslot2.getEndTime().toLocalDateTime();

        return start1.isBefore(end2) && end1.isAfter(start2);
    }
}
