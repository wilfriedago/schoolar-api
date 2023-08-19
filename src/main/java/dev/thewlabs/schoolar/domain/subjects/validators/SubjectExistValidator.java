package dev.thewlabs.schoolar.domain.subjects.validators;

import dev.thewlabs.schoolar.domain.subjects.SubjectRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class SubjectExistValidator implements ConstraintValidator<SubjectExist, String> {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectExistValidator(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void initialize(SubjectExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID subjectId = UUID.fromString(id);
            return subjectRepository.existsById(subjectId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
