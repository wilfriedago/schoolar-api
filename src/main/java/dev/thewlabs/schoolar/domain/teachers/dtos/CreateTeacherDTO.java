package dev.thewlabs.schoolar.domain.teachers.dtos;

import dev.thewlabs.schoolar.shared.validators.EmailUnique;
import dev.thewlabs.schoolar.domain.courses.validators.CourseExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

@Schema(name = "CreateTeacherDto", description = "Create Teacher data transfer object")
public record CreateTeacherDTO(
        @Schema(description = "Teacher first name", example = "John")
        @NotEmpty(message = "Teacher first name can be empty")
        String firstName,

        @Schema(description = "Teacher middle name", example = "Doe")
        String middleName,

        @Schema(description = "Teacher last name", example = "Smith")
        @NotBlank(message = "Teacher last name cannot be blank")
        String lastName,

        @Schema(description = "Teacher email", example = "johndoe@smith.com")
        @NotBlank(message = "Teacher email cannot be blank")
        @Email
        @EmailUnique
        String email,

        @Schema(description = "Teacher phone number", example = "+1 555 555 5555")
        String phoneNumber,

        @Schema(description = "Teacher avatar url", example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
        @URL
        String avatarUrl,

        @Schema(description = "Teacher course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        @UUID
        @CourseExist
        String courseId
) {
}
