package dev.thewlabs.schoolar.domain.admins.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "AdminDetailsDto", description = "Admin details data transfer object.")
public final class AdminDetailsDto {
    @Schema(description = "Admin id.")
    private final String id;

    @Schema(description = "Admin name.")
    private final String name;

    @Schema(description = "Admin email.")
    private final String email;

    @Schema(description = "Admin password.")
    private final String password;
}
