package dev.thewlabs.schoolar.shared.http;

import dev.thewlabs.schoolar.shared.errors.ApiError;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class HttpResponse {
    private HttpResponse() {
    }

    public static <T> @NotNull ResponseEntity<T> forbidden(T body) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    public static <T> @NotNull ResponseEntity<T> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public static <T> @NotNull ResponseEntity<T> unauthorized(T body) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    public static <T> @NotNull ResponseEntity<T> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public static <T> @NotNull ResponseEntity<T> created(T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static @NotNull ResponseEntity<ApiError> fromException(@NotNull ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode().value())
                .body(new ApiError(exception.getReason(), exception.getStatusCode().value()));
    }

    public static <T> @NotNull ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> @NotNull ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> @NotNull ResponseEntity<T> notFound() {
        return ResponseEntity.notFound().build();
    }

    public static <T> @NotNull ResponseEntity<T> badRequest(T body) {
        return ResponseEntity.badRequest().body(body);
    }

    public static <T> @NotNull ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    public static <T> @NotNull ResponseEntity<T> noContent() {
        return ResponseEntity.noContent().build();
    }
}
