package dev.thewlabs.schoolar.domain.courses.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {CourseValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseExist {
    String message() default "The course id provided is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
