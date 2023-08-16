package dev.thewlabs.schoolar.domain.teachers.dtos;

import dev.thewlabs.schoolar.domain.users.dtos.UserDTO;
import dev.thewlabs.schoolar.domain.users.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
@Schema(name = "TeacherDto", description = "Teacher data transfer object")
public class TeacherDTO extends UserDTO {
    public TeacherDTO(UUID id, String firstName, String middleName, String lastName, String email, String phoneNumber, String username, String avatarUrl, UserType type) {
        super(id, firstName, middleName, lastName, email, phoneNumber, username, avatarUrl, type);
    }
}
