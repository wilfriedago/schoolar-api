package dev.thewlabs.schoolar.domain.admins.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Schema(name = "CreateAdminDto", description = "Create Admin data transfer object")
public record CreateAdminDto(
        @Schema(description = "Admin first name", example = "John")
        @NotEmpty(message = "Admin first name can be empty")
        String firstName,

        @Schema(description = "Admin middle name", example = "Doe")
        String middleName,

        @Schema(description = "Admin last name", example = "Smith")
        @NotEmpty(message = "Admin last name can be empty")
        String lastName,

        @Schema(description = "Admin email", example = "johndoe@smith.com")
        @NotEmpty(message = "Admin email can be empty")
        @Email
        String email,

        @Schema(description = "Admin phone number", example = "+1 555 555 5555")
        String phoneNumber,

        @Schema(description = "Admin username", example = "johndoe")
        @NotEmpty(message = "Admin username can be empty")
        String username,

        @Schema(description = "Admin avatar url", example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
        @URL
        String avatarUrl
) {
}
