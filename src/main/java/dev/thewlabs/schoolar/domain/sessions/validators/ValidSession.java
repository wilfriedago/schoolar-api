package dev.thewlabs.schoolar.domain.sessions.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidSessionValidator.class)
public @interface ValidSession {
    String message() default "Invalid session parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
