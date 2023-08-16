package dev.thewlabs.schoolar.domain.courses.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CourseDto", description = "Course data transfer object.")
public class CourseDTO {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Mathematics course for group 1")
    String name;

    @Schema(example = "A mathematics course schedule for a group")
    String description;

    @Schema(example = "30")
    Integer hours;
}
