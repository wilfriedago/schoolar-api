package dev.thewlabs.schoolar.domain.subjects.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {SubjectExistValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubjectExist {
    String message() default "The Subject id provided is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
