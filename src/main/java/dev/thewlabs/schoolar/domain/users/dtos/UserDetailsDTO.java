package dev.thewlabs.schoolar.domain.users.dtos;

import dev.thewlabs.schoolar.common.iam.management.dtos.OrganizationDto;
import dev.thewlabs.schoolar.domain.users.enums.UserGender;
import dev.thewlabs.schoolar.domain.users.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserDetailsDTO", description = "User details data transfer object")
public class UserDetailsDTO {
    @Schema(description = "User id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
    UUID id;

    @Schema(description = "User first name", example = "John")
    String firstName;

    @Schema(description = "User middle name", example = "Doe")
    String middleName;

    @Schema(description = "User last name", example = "Smith")
    String lastName;

    @Schema(description = "User email", example = "johndoe@smith.com")
    String email;

    @Schema(description = "User phone number", example = "+1 555 555 5555")
    String phoneNumber;

    @Schema(description = "User username", example = "johndoe")
    String username;

    @Schema(description = "User avatar url", example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
    String avatarUrl;

    @Schema(description = "User type", example = "STUDENT")
    UserType type;

    @Schema(description = "User gender", example = "MALE")
    UserGender gender;

    @Schema(description = "User birth date", example = "2021-01-01")
    LocalDate birthDate;

    @Schema(description = "User address", example = "Cotonou, Benin")
    String address;

    @Schema(description = "User roles", example = "[\"8v4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a\", \"9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a\"]")
    List<UUID> roles;

    @Schema(description = "User permissions", example = "[\"8v4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a\", \"9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a\"]")
    List<UUID> permissions;

    @Schema(description = "User status", example = "ACTIVE")
    String status;

    @Schema(description = "User owned organizations", implementation = OrganizationDto.class)
    List<OrganizationDto> ownedOrganizations;

    @Schema(description = "User organizations", implementation = OrganizationDto.class)
    List<OrganizationDto> organizations;

    @Schema(description = "User email verified", example = "true")
    Boolean emailVerified;

    @Schema(description = "User phone number verified", example = "true")
    Boolean phoneNumberVerified;

    @Schema(description = "User created at", example = "2021-01-01T00:00:00.000Z")
    ZonedDateTime createdAt;
}
