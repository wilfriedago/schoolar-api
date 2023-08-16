package dev.thewlabs.schoolar.shared.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RandomUtils {
    private static final java.util.Random JAVA_RANDOM = new java.util.Random();

    public static <T> T element(@NotNull List<T> list) {
        return list.get(JAVA_RANDOM.nextInt(list.size()));
    }
}
