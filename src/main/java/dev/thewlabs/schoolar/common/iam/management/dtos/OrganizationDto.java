package dev.thewlabs.schoolar.common.iam.management.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "OrganizationDto", description = "Organization data transfer object")
public record OrganizationDto(
        @Schema(description = "Organization id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        UUID id,
        @Schema(description = "Organization name", example = "Schoolar")
        String name,
        @Schema(description = "Organization description", example = "Schoolar is a school management system")
        String description,
        @Schema(description = "Organization status", example = "ACTIVE")
        String status,
        @Schema(description = "Organization members count", example = "10")
        int membersCount,
        @Schema(description = "Organization created at", example = "2021-01-01T00:00:00.000Z")
        String createdAt
) {
}
