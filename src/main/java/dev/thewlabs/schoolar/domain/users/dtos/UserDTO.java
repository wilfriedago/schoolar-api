package dev.thewlabs.schoolar.domain.users.dtos;

import dev.thewlabs.schoolar.domain.users.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UserDto", description = "User data transfer dto")
public class UserDTO {
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
}
