package dev.thewlabs.schoolar.domain.sessions.validators;

import dev.thewlabs.schoolar.domain.classrooms.Classroom;
import dev.thewlabs.schoolar.domain.classrooms.ClassroomRepository;
import dev.thewlabs.schoolar.domain.courses.Course;
import dev.thewlabs.schoolar.domain.courses.CourseRepository;
import dev.thewlabs.schoolar.domain.sessions.dtos.CreateSessionDto;
import dev.thewlabs.schoolar.domain.timetables.entities.ClassroomTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.GroupTimetable;
import dev.thewlabs.schoolar.domain.timetables.entities.Timeslot;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static dev.thewlabs.schoolar.infra.constants.ApplicationConstants.EVENT_MIN_DURATION;
import static java.util.Objects.isNull;

// TODO : Too heavy ! #NeedRefactoring
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
        if (isNull(dto)) return true; // Allow null values to be validated elsewhere if needed

        Timeslot eventTimeslot = new Timeslot(dto.startTime(), dto.endTime());

        Course course = courseRepository.findCourseById(UUID.fromString(dto.courseId()));
        Classroom classroom = classroomRepository.findClassroomById(UUID.fromString(dto.classroomId()));

        GroupTimetable groupTimetable = course.getGroup().findTimeslotTimetable(eventTimeslot).orElse(null);
        ClassroomTimetable classroomTimetable = classroom.findTimeslotTimetable(eventTimeslot).orElse(null);

        if (Boolean.TRUE.equals(eventTimeslot.isStartEqualsEnd())) {
            addValidationError(context, "Session start time can't be the same as session end time.");
            return false;
        }

        if (Boolean.TRUE.equals(eventTimeslot.isEndBeforeStart())) {
            addValidationError(context, "Session end time can't be before start time.");
            return false;
        }

        if (Boolean.TRUE.equals(eventTimeslot.isDurationValid())) {
            addValidationError(context, "Session duration must be at least " + EVENT_MIN_DURATION + " minutes long.");
            return false;
        }

        if (classroom.getCapacity() < course.getAttendees().size()) {
            addValidationError(context, "The chosen classroom cannot host all the attendees for this session.");
            return false;
        }

        if (!isNull(groupTimetable) && groupTimetable.isTimeslotBooked(eventTimeslot)) {
            //TODO: Change this message, make it more descriptive. #AskChatGPT
            addValidationError(context, "The targeted group timetable cannot allowed this session.");
            return false;
        }

        if (!isNull(classroomTimetable) && classroomTimetable.isTimeslotBooked(eventTimeslot)) {
            //TODO: Change this message, make it more descriptive. #AskChatGPT
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
