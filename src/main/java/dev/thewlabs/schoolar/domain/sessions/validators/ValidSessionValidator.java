package dev.thewlabs.schoolar.domain.sessions.validators;

import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDto;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidSessionValidator implements ConstraintValidator<ValidSession, CreateSessionDto> {
    private final CourseRepository courseRepository;
    private final ClassroomRepository classroomRepository;

    public ValidSessionValidator(CourseRepository courseRepository, ClassroomRepository classroomRepository) {
        this.courseRepository = courseRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void initialize(ValidSession constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateSessionDto dto, ConstraintValidatorContext context) {
        if (dto == null) return true; // Allow null values to be validated elsewhere if needed
        if (dto.timeslot() == null) return true; // Allow null values to be validated elsewhere if needed

        Course course = courseRepository.findCourseById(UUID.fromString(dto.courseId()));
        Classroom classroom = classroomRepository.findClassroomById(UUID.fromString(dto.classroomId()));

        GroupTimetable groupTimetable = course.getGroup().findTimeslotTimetable(dto.timeslot()).orElse(null);
        ClassroomTimetable classroomTimetable = classroom.findTimeslotTimetable(dto.timeslot()).orElse(null);

        if (classroom.getCapacity() < course.getAttendees().size()) {
            addValidationError(context, "The chosen classroom cannot host all the attendees for this session.");
            return false;
        }

        if (groupTimetable != null && !groupTimetable.isTimeslotFree(dto.timeslot())) {
            addValidationError(context, "The targeted group timetable cannot allowed this session.");
            return false;
        }

        if (classroomTimetable != null && !classroomTimetable.isTimeslotFree(dto.timeslot())) {
            addValidationError(context, "The targeted classroom timetable cannot allowed this session.");
            return false;
        }

        return true;
    }

    private void addValidationError(@NotNull ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
