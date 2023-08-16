package dev.thewlabs.schoolar.domain.classrooms.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Schema(name = "CreateClassroomDTO", description = "Create classroom data transfer object")
public record CreateClassroomDTO(
        @Schema(example = "CL-302")
        @NotEmpty(message = "Classroom name cannot be empty")
        String name,

        @Schema(example = "Chemistry classroom equipped with lab tables, fume hoods, and seating for 25 students")
        String description,

        @Schema(example = "25")
        @Positive
        int capacity
) {
}
