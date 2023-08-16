package dev.thewlabs.schoolar.shared.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

@Getter
public class ApiError {
    private static final String DEFAULT_ERROR_MESSAGE = "Check the 'errors' property for more details.";
    public final Collection<ValidationError> errors;
    @Schema(example = "generic error message")
    private final String message;
    @Schema(example = "999")
    private final Integer status;

    public ApiError(String message, Integer status) {
        this.message = message;
        this.status = status;
        this.errors = List.of();
    }

    public ApiError(String message, @NotNull HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.errors = List.of();
    }

    public ApiError(@NotNull HttpStatus status, Collection<ValidationError> errors) {
        this.message = ApiError.DEFAULT_ERROR_MESSAGE;
        this.status = status.value();
        this.errors = errors;
    }

    public ApiError(@NotNull HttpStatus status, String error) {
        this.message = ApiError.DEFAULT_ERROR_MESSAGE;
        this.status = status.value();
        this.errors = List.of(new ValidationError(error));
    }

    public ApiError(@NotNull HttpStatus status, ValidationError error) {
        this.message = ApiError.DEFAULT_ERROR_MESSAGE;
        this.status = status.value();
        this.errors = List.of(error);
    }
}
