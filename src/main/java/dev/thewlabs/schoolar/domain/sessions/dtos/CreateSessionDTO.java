package dev.thewlabs.schoolar.domain.sessions.dtos;

import dev.thewlabs.schoolar.domain.classrooms.validators.ClassroomExist;
import dev.thewlabs.schoolar.domain.courses.validators.CourseExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;

@Schema(name = "CreateSessionDTO", description = "Create Session of a course request data transfer object.")
public record CreateSessionDTO(
        @NotBlank(message = "The title of the session cannot be blank.")
        @Schema(description = "The title of the session.", example = "Maths session")
        String title,

        @Schema(description = "A description of the session, limited to a maximum length of 1000 characters.", example = "This is a maths session")
        String description,

        @Validated
        @FutureOrPresent
        ZonedDateTime startTime,

        @Validated
        @Future
        ZonedDateTime endTime,

        @NotEmpty(message = "Session course id cannot be empty")
        @UUID
        @CourseExist
        @Schema(description = "Session course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        String courseId,

        @NotEmpty(message = "Session classroom id cannot be empty")
        @UUID
        @ClassroomExist
        @Schema(description = "Session course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        String classroomId
) {
}
