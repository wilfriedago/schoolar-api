package dev.thewlabs.schoolar.domain.classrooms.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateClassroomDTO", description = "Update classroom data transfer object")
public record UpdateClassroomDTO(
        @Schema(example = "CL-302")
        String name,

        @Schema(example = "Chemistry classroom equipped with lab tables, fume hoods, and seating for 25 students")
        String description,

        @Schema(example = "25")
        int capacity
) {
}
