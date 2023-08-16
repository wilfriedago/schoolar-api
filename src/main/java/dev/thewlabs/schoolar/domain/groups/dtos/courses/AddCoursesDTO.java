package dev.thewlabs.schoolar.domain.groups.dtos.courses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Schema(name = "AddCoursesDTO", description = "Add courses to group request data transfer object.")
public record AddCoursesDTO(
        @Schema(example = "[\"9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a\" , \"9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2b\"]")
        @NotEmpty(message = "Course ids list cannot be empty")
        List<UUID> courseIds
) {
}
