package dev.thewlabs.schoolar.domain.timetables.utils;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.entities.Timetable;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimetables;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TimetableUtils {
    private TimetableUtils() {
    }

    public static <T extends Timetable> @NotNull Optional<T> findTimeslotTimetable(@NotNull Timeslot timeslot, @NotNull HasTimetables<T> entity) {
        return entity.getTimetables().stream()
                .filter(timetable ->
                        (timeslot.getStartTime().toLocalDate().isAfter(timetable.getStartDate()) || timeslot.getStartTime().toLocalDate().isEqual(timetable.getStartDate()))
                                && (timeslot.getEndTime().toLocalDate().isBefore(timetable.getEndDate()) || timeslot.getEndTime().toLocalDate().isEqual(timetable.getEndDate()))
                ).findFirst();
    }
}
