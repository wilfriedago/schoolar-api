package dev.thewlabs.schoolar.domain.groups.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "GroupDTO", description = "Group data transfer object")
public class GroupDTO {
    @Schema(example = "9a7c1b9e-1b1a-4b0e-8b0a-9b0c1d9e1f2a")
    UUID id;

    @Schema(example = "Wonderful group")
    String name;

    @Schema(example = "Wonderful group located in Cotonou")
    String description;
}


