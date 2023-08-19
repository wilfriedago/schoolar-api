package dev.thewlabs.schoolar.domain.courses.dtos;

import dev.thewlabs.schoolar.domain.groups.validators.GroupExist;
import dev.thewlabs.schoolar.domain.subjects.validators.SubjectExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;

@Schema(name = "CreateCourse", description = "Create course request payload")
public record CreateCourseDto(
        @Schema(example = "Mathematics Course")
        @NotBlank(message = "Course name cannot be blank.")
        String name,

        @Schema(example = "Optional description of the course")
        String description,

        @Schema(example = "20")
        @Validated
        @Min(value = 0, message = "Course hours must be greater than or equal to 0")
        Integer hours,

        @Schema(example = "subject_id_here")
        @NotBlank(message = "Course subject id cannot be blank.")
        @UUID(message = "Course subject should be a valid UUID.")
        @SubjectExist
        String subjectId,

        @Schema(example = "group_id_here")
        @NotBlank(message = "Course group id cannot be blank.")
        @UUID(message = "Course group id should be a valid UUID.")
        @GroupExist
        String groupId
) {
}
