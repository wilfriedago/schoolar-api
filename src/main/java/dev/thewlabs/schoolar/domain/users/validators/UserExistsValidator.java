package dev.thewlabs.schoolar.domain.users.validators;

import dev.thewlabs.schoolar.domain.users.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class UserExistsValidator implements ConstraintValidator<UserExists, String> {
    private final UserRepository userRepository;

    @Autowired
    public UserExistsValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID userId = UUID.fromString(id);
            return this.userRepository.existsById(userId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
