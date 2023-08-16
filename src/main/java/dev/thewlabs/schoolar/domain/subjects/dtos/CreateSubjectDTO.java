package dev.thewlabs.schoolar.domain.subjects.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "CreateSubjectDto", description = "Create subject data transfer object")
public record CreateSubjectDTO(
        @Schema(example = "Mathematics")
        @NotEmpty(message = "Subject name cannot be empty")
        String name,

        @Schema(example = "The study of numbers, equations, functions, and geometric shapes (see geometry) and their relationships. Some branches of mathematics are characterized by use of strict proofs based on axioms. Others, such as statistics, are characterized by rigorous argumentation but not by the use of strict proofs.")
        String description
) {

}
