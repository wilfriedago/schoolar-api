package dev.thewlabs.schoolar.domain.groups.dtos.students;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Schema(name = "RemoveStudentsDTO", description = "Remove students from group request data transfer object.")
public record RemoveStudentsDTO(
        @Schema(example = "[\"9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a\" , \"9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2b\"]")
        @NotEmpty(message = "Student ids list cannot be empty")
        List<UUID> studentIds
) {
}
