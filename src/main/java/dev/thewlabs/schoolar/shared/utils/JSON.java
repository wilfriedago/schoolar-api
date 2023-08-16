package dev.thewlabs.schoolar.shared.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jetbrains.annotations.Nullable;

public class JSON {
    private static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private JSON() {
    }

    public static <T> @Nullable String stringify(final T object) {
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null; // Handle the exception appropriately
        }
    }
}
