package dev.thewlabs.schoolar.infra.constants;

import java.time.DayOfWeek;
import java.time.Duration;

public class ApplicationConstants {
    public static final String APP_NAME = "Schoolar";
    public static final Duration EVENT_MIN_DURATION = Duration.ofMinutes(30);
    public static final Duration EVENT_MAX_DURATION = Duration.ofHours(24);
    public static final DayOfWeek WEEK_START_DAY = DayOfWeek.MONDAY;
    public static final DayOfWeek WEEK_LAST_DAY = DayOfWeek.SUNDAY;
    private ApplicationConstants() {
    }
}
