package dev.thewlabs.schoolar.domain.students.dtos;

import dev.thewlabs.schoolar.domain.groups.validators.GroupExist;
import dev.thewlabs.schoolar.shared.validators.EmailUnique;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

@Schema(name = "CreateStudentDto", description = "Create Student data transfer object")
public record CreateStudentDTO(
        @Schema(description = "Student first name", example = "John")
        @NotEmpty(message = "Student first name can be empty")
        String firstName,

        @Schema(description = "Student middle name", example = "Doe")
        String middleName,

        @Schema(description = "Student last name", example = "Smith")
        @NotEmpty(message = "Student last name can be empty")
        String lastName,

        @Schema(description = "Student email", example = "johndoe@smith.com")
        @NotEmpty(message = "Student email can be empty")
        @Email
        @EmailUnique
        String email,

        @Schema(description = "Student phone number", example = "+1 555 555 5555")
        String phoneNumber,

        @Schema(description = "Student username", example = "johndoe")
        @NotEmpty(message = "Student username can be empty")
        String username,

        @Schema(description = "Student avatar url", example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
        @URL
        String avatarUrl,

        @Schema(description = "Student group id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        @UUID
        @GroupExist
        String groupId
) {
}
