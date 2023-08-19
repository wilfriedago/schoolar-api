package dev.thewlabs.schoolar.domain.timetables.utils;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import dev.thewlabs.schoolar.domain.timetables.entities.Timetable;
import dev.thewlabs.schoolar.domain.timetables.interfaces.HasTimetables;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Optional;

public class TimetableUtils {
    private TimetableUtils() {
    }

    public static <T extends Timetable> @NotNull Optional<T> findTimeslotTimetable(@NotNull Timeslot timeslot, @NotNull HasTimetables<T> entity) {
        Date timeslotStartDate = Date.from(timeslot.getStartTime().toInstant());
        Date timeslotEndDate = Date.from(timeslot.getEndTime().toInstant());

        return entity.getTimetables().stream()
                .filter(timetable ->
                        timeslotStartDate.compareTo(timetable.getStartDate()) >= 0 && timeslotEndDate.compareTo(timetable.getEndDate()) <= 0)
                .findFirst();
    }
}
