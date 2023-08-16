package dev.thewlabs.schoolar.shared.utils;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean notNullOrBlank(String string) {
        if (Objects.isNull(string)) {
            return false;
        }
        return !string.isBlank();
    }

    public static @NotNull Boolean noneOfThenNullOrEmpty(String... strings) {
        return Stream.of(strings).allMatch(StringUtils::notNullOrBlank);
    }
}
