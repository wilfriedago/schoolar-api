package dev.thewlabs.schoolar.domain.classrooms.validators;

import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class ClassroomExistValidator implements ConstraintValidator<ClassroomExist, String> {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomExistValidator(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void initialize(ClassroomExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID classroomId = UUID.fromString(id);
            return this.classroomRepository.existsById(classroomId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
