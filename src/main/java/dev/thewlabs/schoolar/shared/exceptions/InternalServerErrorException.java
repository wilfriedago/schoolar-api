package dev.thewlabs.schoolar.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorException extends ResponseStatusException {
    public InternalServerErrorException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
