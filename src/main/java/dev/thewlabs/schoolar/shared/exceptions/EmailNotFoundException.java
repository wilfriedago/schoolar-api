package dev.thewlabs.schoolar.shared.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    /**
     * Constructs a <code>UsernameNotFoundException</code> with the specified message.
     *
     * @param msg the detail message.
     */
    public EmailNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code UsernameNotFoundException} with the specified message and root
     * cause.
     *
     * @param msg   the detail message.
     * @param cause root cause
     */
    public EmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
