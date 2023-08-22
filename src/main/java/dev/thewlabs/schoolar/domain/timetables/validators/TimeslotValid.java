package dev.thewlabs.schoolar.domain.timetables.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeslotValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface TimeslotValid {
    String message() default "Invalid timeslot";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
