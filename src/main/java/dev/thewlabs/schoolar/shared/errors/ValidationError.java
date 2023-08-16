package dev.thewlabs.schoolar.shared.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter
public class ValidationError {
    @Schema(example = "genericFieldName")
    private final String field;

    @Schema(example = "generic error message")
    private final String message;

    public ValidationError(String message) {
        this.field = null;
        this.message = message;
    }

    public ValidationError(@NotNull FieldError error) {
        this.field = error.getField();
        this.message = error.getDefaultMessage();
    }

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static List<ValidationError> of(@NotNull MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> (new ValidationError((FieldError) error)))
                .toList();
    }
}
