package dev.thewlabs.schoolar.domain.events.validators;

import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.events.Event;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidEventValidator implements ConstraintValidator<ValidEvent, Event> {
    private final CourseRepository courseRepository;

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ValidEventValidator(CourseRepository courseRepository, ClassroomRepository classroomRepository) {
        this.courseRepository = courseRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void initialize(ValidEvent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Event value, ConstraintValidatorContext context) {
        return false;
    }
}
