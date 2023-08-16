package dev.thewlabs.schoolar.shared.exceptions;

import dev.thewlabs.schoolar.shared.errors.ValidationError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

// TODO: Finish this implementation
@Getter
public class BadRequestException extends RuntimeException {
    final transient Collection<ValidationError> errors;

    public BadRequestException() {
        this.errors = new ArrayList<>();
    }

    public BadRequestException(Collection<ValidationError> errors) {
        this.errors = errors;
    }

    public BadRequestException(String message, Collection<ValidationError> errors) {
        super(message);
        this.errors = errors;
    }

    public BadRequestException(String message, Throwable cause, Collection<ValidationError> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public BadRequestException(Throwable cause, Collection<ValidationError> errors) {
        super(cause);
        this.errors = errors;
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                               Collection<ValidationError> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }

    public void add(ValidationError error) {
        this.errors.add(error);
    }

    public Boolean hasError() {
        return !this.errors.isEmpty();
    }
}
