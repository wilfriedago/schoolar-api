package dev.thewlabs.schoolar.domain.events.validators;

import dev.thewlabs.schoolar.domain.events.EventRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class EventExistsValidator implements ConstraintValidator<EventExists, String> {
    private final EventRepository eventRepository;

    @Autowired
    public EventExistsValidator(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void initialize(EventExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID eventId = UUID.fromString(id);
            return this.eventRepository.existsById(eventId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
