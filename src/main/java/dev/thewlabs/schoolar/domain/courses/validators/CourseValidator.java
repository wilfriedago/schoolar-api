package dev.thewlabs.schoolar.domain.courses.validators;

import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class CourseValidator implements ConstraintValidator<CourseExist, String> {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void initialize(CourseExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (isNull(id)) return true;// Let other validators handle null value

        try {
            UUID courseId = UUID.fromString(id);
            return courseRepository.existsById(courseId);
        } catch (IllegalArgumentException exception) {
            return true; // Let other validators handle uuid validation
        }
    }
}
