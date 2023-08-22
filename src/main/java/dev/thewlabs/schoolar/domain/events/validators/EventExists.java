package dev.thewlabs.schoolar.domain.events.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {EventExistsValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventExists {
    String message() default "The event id provided is invalid.";

    Class<?>[] events() default {};

    Class<? extends Payload>[] payload() default {};
}
