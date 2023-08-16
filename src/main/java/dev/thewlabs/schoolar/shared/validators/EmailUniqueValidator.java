package dev.thewlabs.schoolar.shared.validators;

import dev.thewlabs.schoolar.common.iam.authentication.services.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {
    private final AuthService authService;

    @Autowired
    public EmailUniqueValidator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (isNull(email)) {
            return true;// Let other validators handle null value
        }

        return !this.authService.existsByEmail(email);
    }
}
