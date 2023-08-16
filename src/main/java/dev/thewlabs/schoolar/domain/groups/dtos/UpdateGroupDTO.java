package dev.thewlabs.schoolar.domain.groups.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateGroupDTO", description = "Update group request data transfer object.")
public record UpdateGroupDTO(
        @Schema(example = "Wonderful group")
        String name,
        @Schema(example = "Wonderful group located in Cotonou")
        String description
) {
}
