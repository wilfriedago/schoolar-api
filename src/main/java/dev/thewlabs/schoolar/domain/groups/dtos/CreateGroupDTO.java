package dev.thewlabs.schoolar.domain.groups.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "CreateGroupDTO", description = "Create group request data transfer object.")
public record CreateGroupDTO(
        @Schema(example = "Wonderful group")
        @NotEmpty(message = "Group name cannot be empty")
        String name,

        @Schema(example = "Wonderful group located in Cotonou")
        String description
) {
}
