package dev.thewlabs.schoolar.shared.handlers;

import dev.thewlabs.schoolar.shared.errors.ApiError;
import dev.thewlabs.schoolar.shared.errors.ValidationError;
import dev.thewlabs.schoolar.shared.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@RestControllerAdvice
public class GlobalErrorHandler {
    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Collection<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ValidationError.of(exception);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> status(ResponseStatusException exception) {
        return HttpResponse.fromException(exception);
    }

    @ResponseStatus(code = UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body("Unauthorized access");
    }
}
