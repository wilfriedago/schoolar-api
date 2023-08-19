package dev.thewlabs.schoolar.domain.events.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEventValidator.class)
public @interface ValidEvent {
    String message() default "Invalid session parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
