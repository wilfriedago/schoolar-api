package dev.thewlabs.schoolar.shared.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {EmailUniqueValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnique {
    String message() default "This email is already used.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
