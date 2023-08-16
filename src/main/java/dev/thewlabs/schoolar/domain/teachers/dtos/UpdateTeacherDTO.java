package dev.thewlabs.schoolar.domain.teachers.dtos;

import dev.thewlabs.schoolar.domain.courses.validators.CourseExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

@Schema(name = "UpdateTeacherDto", description = "Update Teacher data transfer object")
public record UpdateTeacherDTO(
        @Schema(description = "Teacher first name", example = "John")
        String firstName,

        @Schema(description = "Teacher middle name", example = "Doe")
        String middleName,

        @Schema(description = "Teacher last name", example = "Smith")
        String lastName,

        @Schema(description = "Teacher email", example = "johndoe@smith.com")
        @Email
        String email,

        @Schema(description = "Teacher phone number", example = "+1 555 555 5555")
        String phoneNumber,

        @Schema(description = "Teacher username", example = "johndoe")
        String username,

        @Schema(description = "Teacher avatar url", example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
        @URL
        String avatarUrl,

        @Schema(description = "Teacher course id", example = "9c4b1b9e-5b7a-4b1a-8b0a-5b9a4b1a8b0a")
        @UUID
        @CourseExist
        String courseId
) {
}
