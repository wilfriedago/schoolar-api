package dev.thewlabs.schoolar.domain.users.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = {UserExistsValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExists {
    String message() default "The user id provided is invalid.";

    Class<?>[] users() default {};

    Class<? extends Payload>[] payload() default {};
}
