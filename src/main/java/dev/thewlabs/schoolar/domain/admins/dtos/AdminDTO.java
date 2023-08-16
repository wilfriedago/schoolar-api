package dev.thewlabs.schoolar.domain.admins.dtos;

import dev.thewlabs.schoolar.domain.users.dtos.UserDTO;
import dev.thewlabs.schoolar.domain.users.enums.UserType;

import java.util.UUID;

public class AdminDTO extends UserDTO {
    public AdminDTO(UUID id, String firstName, String middleName, String lastName, String email, String phoneNumber, String username, String avatarUrl, UserType type) {
        super(id, firstName, middleName, lastName, email, phoneNumber, username, avatarUrl, type);
    }
}
