package dev.thewlabs.schoolar.domain.groups.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {GroupValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupExist {
    String message() default "The group id provided is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
