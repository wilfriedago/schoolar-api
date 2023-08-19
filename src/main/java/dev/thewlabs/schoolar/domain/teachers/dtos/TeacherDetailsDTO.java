package dev.thewlabs.schoolar.domain.teachers.dtos;

import dev.thewlabs.schoolar.domain.courses.dtos.CourseDto;
import dev.thewlabs.schoolar.domain.users.enums.UserType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Schema(name = "TeacherDto", description = "Teacher data transfer object.")
public final class TeacherDetailsDTO extends TeacherDTO {
    @ArraySchema(schema = @Schema(description = "Teacher courses"), contains = @Schema(implementation = CourseDto.class))
    List<CourseDto> courses;

    @Schema(description = "Teacher created at", example = "2021-01-01T00:00:00.000Z")
    ZonedDateTime createdAt;

    public TeacherDetailsDTO(
            UUID id,
            String firstName,
            String middleName,
            String lastName,
            String email,
            String phoneNumber,
            String username,
            String avatarUrl,
            UserType type,
            List<CourseDto> courses,
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

        this.courses = courses;
        this.createdAt = createdAt;
    }
}
