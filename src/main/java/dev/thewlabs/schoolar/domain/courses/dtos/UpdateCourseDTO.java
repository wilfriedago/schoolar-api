package dev.thewlabs.schoolar.domain.courses.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.UUID;

@Schema(name = "UpdateCourse", description = "Update course request payload")
public record UpdateCourseDTO(
        @Schema(example = "Mathematics Course")
        String name,
        @Schema(example = "Updated description of the course")
        String description,
        @Schema(example = "20")
        @Min(value = 0, message = "Course hours must be greater than or equal to 0")
        Integer hours,
        @Schema(example = "subject_id_here")
        @UUID(message = "Course subject should be a valid UUID")
        String subjectId,
        @Schema(example = "group_id_here")
        @UUID(message = "Course subject should be a valid UUID")
        String groupId
) {
}
