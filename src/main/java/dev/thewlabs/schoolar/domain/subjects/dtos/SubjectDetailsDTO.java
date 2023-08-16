package dev.thewlabs.schoolar.domain.subjects.dtos;

import dev.thewlabs.schoolar.domain.courses.dtos.CourseDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SubjectDetailsDto", description = "Subject details data transfer object")
public class SubjectDetailsDTO {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Mathematics")
    String name;

    @Schema(example = "The study of numbers, equations, functions, and geometric shapes (see geometry) and their relationships.")
    String description;

    @ArraySchema(contains = @Schema(implementation = CourseDTO.class))
    List<CourseDTO> courses;

    ZonedDateTime createdAt;
}
