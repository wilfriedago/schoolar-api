package dev.thewlabs.schoolar.domain.classrooms.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {ClassroomExistValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassroomExist {
    String message() default "The classroom id provided is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
