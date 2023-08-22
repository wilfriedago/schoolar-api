package dev.thewlabs.schoolar.shared.utils;

import dev.thewlabs.schoolar.infra.constants.ApplicationConstants;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class DatetimeUtils {
    // Private constructor to prevent instantiation
    private DatetimeUtils() {
    }

    public static LocalDate getFirstDayOfWeek() {
        LocalDate firstDayOfWeek = LocalDate.now();
        while (firstDayOfWeek.getDayOfWeek() != ApplicationConstants.WEEK_START_DAY) {
            firstDayOfWeek = firstDayOfWeek.minusDays(1);
        }

        return firstDayOfWeek;
    }

    public static LocalDate getFirstDayOfWeek(LocalDate date) {
        LocalDate firstDayOfWeek = date;
        while (firstDayOfWeek.getDayOfWeek() != ApplicationConstants.WEEK_START_DAY) {
            firstDayOfWeek = firstDayOfWeek.minusDays(1);
        }

        return firstDayOfWeek;
    }

    public static LocalDate getLastDayOfWeek(LocalDate date) {
        LocalDate lastDayOfWeek = date;
        while (lastDayOfWeek.getDayOfWeek() != ApplicationConstants.WEEK_LAST_DAY) {
            lastDayOfWeek = lastDayOfWeek.plusDays(1);
        }

        return lastDayOfWeek;
    }

    public static LocalDate getLastDayOfWeek() {
        LocalDate lastDayOfWeek = LocalDate.now();
        while (lastDayOfWeek.getDayOfWeek() != ApplicationConstants.WEEK_LAST_DAY) {
            lastDayOfWeek = lastDayOfWeek.plusDays(1);
        }

        return lastDayOfWeek;
    }

    public static @NotNull Map<String, LocalDate> getFirstAndLastDayOfWeek(LocalDate date) {
        HashMap<String, LocalDate> week = new HashMap<>();

        week.put("first", getLastDayOfWeek(date));
        week.put("last", getLastDayOfWeek(date));

        return week;
    }
}
