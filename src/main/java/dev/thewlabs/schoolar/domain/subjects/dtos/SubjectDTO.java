package dev.thewlabs.schoolar.domain.subjects.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SubjectDTO", description = "Subject data transfer object.")
public class SubjectDTO {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Mathematics")
    String name;

    @Schema(example = "The study of numbers, equations, functions, and geometric shapes (see geometry) and their relationships.")
    String description;
}
