package dev.thewlabs.schoolar.domain.subjects.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateSubjectDto", description = "Update subject data transfer object.")
public record UpdateSubjectDTO(
        @Schema(example = "Mathematics")
        String name,

        @Schema(example = "The study of numbers, equations, functions, and geometric shapes (see geometry) and their relationships.")
        String description
) {
}
