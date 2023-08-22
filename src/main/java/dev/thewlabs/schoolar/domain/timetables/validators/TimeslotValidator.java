package dev.thewlabs.schoolar.domain.timetables.validators;

import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jetbrains.annotations.NotNull;

import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MAX_DURATION;
import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MIN_DURATION;

public class TimeslotValidator implements ConstraintValidator<TimeslotValid, Timeslot> {
    @Override
    public void initialize(TimeslotValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Timeslot timeslot, ConstraintValidatorContext context) {
        if (timeslot == null) return true; // Allow null values to be validated elsewhere if needed

        if (!timeslot.isStartDifferentFromEnd()) {
            addValidationError(context, "The start time and end time cannot be the same.");
            return false;
        }

        if (!timeslot.isStartBeforeEnd()) {
            addValidationError(context, "The start time must be before the end time.");
            return false;
        }

        if (!timeslot.isStartAndEndSameDay()) {
            addValidationError(context, "The event start and end time should be the same day.");
            return false;
        }

        if (timeslot.isDurationTooLong()) {
            addValidationError(context, "The event duration cannot be longer than " + EVENT_MAX_DURATION.toString() + " minutes.");
            return false;
        }

        if (timeslot.isDurationTooShort()) {
            addValidationError(context, "The event duration cannot be shorter than " + EVENT_MIN_DURATION.toString() + " minutes.");
            return false;
        }

        return true;
    }

    private void addValidationError(@NotNull ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
