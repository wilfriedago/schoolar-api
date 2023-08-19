package dev.thewlabs.schoolar.domain.sessions.dtos;

import dev.thewlabs.schoolar.domain.classrooms.validators.ClassroomExist;
import dev.thewlabs.schoolar.domain.courses.validators.CourseExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import org.hibernate.validator.constraints.UUID;

@Schema(name = "UpdateSessionDTO", description = "Update Session of a course request data transfer object.")
public record UpdateSessionDto(
        @Schema(description = "The title of the session.", example = "Maths session")
        String title,

        @Schema(description = "A description of the session, limited to a maximum length of 1000 characters.", example = "This is a maths session")
        String description,

        @FutureOrPresent(message = "The start time of the session cannot be in the past.")
        @Schema(description = "The start time of the session. Cannot be null.", example = "2021-01-01T10:00:00")
        String startTime,

        @Future(message = "The end time of the session cannot be in the past.")
        @Schema(description = "The end time of the session. Cannot be null.", example = "2021-01-01T12:00:00")
        String endTime,

        @UUID
        @CourseExist
        @Schema(description = "Session course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        String courseId,

        @UUID
        @ClassroomExist
        @Schema(description = "Session course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        String classroomId
) {
}
