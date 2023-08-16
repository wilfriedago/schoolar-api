package dev.thewlabs.schoolar.shared.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?";
    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final Random random = new SecureRandom();
    private static final int DEFAULT_LENGTH = 12; // Default password length

    public static @NotNull String generate() {
        return generate(DEFAULT_LENGTH);
    }

    public static @NotNull String generate(int length) {
        if (length < 8) throw new IllegalArgumentException("Password length must be at least 8");

        StringBuilder password = new StringBuilder();

        // Ensure at least one lowercase, one uppercase, one digit, and one special character
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(DIGITS));
        password.append(getRandomChar(SPECIAL_CHARACTERS));

        // Fill the remaining length
        int remainingLength = length - password.length();
        for (int i = 0; i < remainingLength; i++) {
            password.append(getRandomChar(ALL_CHARACTERS));
        }

        // Shuffle the characters to ensure randomness
        return shuffleString(password.toString());
    }

    private static char getRandomChar(@NotNull String characterSet) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }

    private static String shuffleString(@NotNull String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static String encode(String password, int strength) {
        return new BCryptPasswordEncoder(strength).encode(password);
    }

    public static boolean matches(String password, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(password, encodedPassword);
    }

    public static boolean matches(String password, String encodedPassword, int strength) {
        return new BCryptPasswordEncoder(strength).matches(password, encodedPassword);
    }
}