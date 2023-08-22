package dev.thewlabs.schoolar.domain.courses.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourseValidator.class)
@Documented
public @interface CourseExist {
    String message() default "The course id provided is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
