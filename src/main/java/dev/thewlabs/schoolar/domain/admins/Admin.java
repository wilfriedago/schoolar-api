package dev.thewlabs.schoolar.domain.admins;

import dev.thewlabs.schoolar.core.interfaces.Serializable;
import dev.thewlabs.schoolar.domain.admins.dtos.AdminDetailsDTO;
import dev.thewlabs.schoolar.domain.admins.dtos.AdminDTO;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Admin extends User implements Serializable<AdminDTO, AdminDetailsDTO> {
    @Override
    public AdminDTO serialize() {
        return null;
    }

    @Override
    public AdminDetailsDTO serializeWithDetails() {
        return null;
    }
}
