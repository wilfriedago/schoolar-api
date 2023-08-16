package dev.thewlabs.schoolar.domain.admins.dtos;

import dev.thewlabs.schoolar.domain.users.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Schema(name = "AdminDetailsDto", description = "Admin details data transfer object.")
public final class AdminDetailsDTO extends AdminDTO {
    @Schema(description = "Admin created at", example = "2021-01-01T00:00:00.000Z")
    ZonedDateTime createdAt;

    public AdminDetailsDTO(
            UUID id,
            String firstName,
            String middleName,
            String lastName,
            String email,
            String phoneNumber,
            String username,
            String avatarUrl,
            UserType type,
            ZonedDateTime createdAt
    ) {
        super(
                id,
                firstName,
                middleName,
                lastName,
                email,
                phoneNumber,
                username,
                avatarUrl,
                type
        );

        this.createdAt = createdAt;
    }
}
