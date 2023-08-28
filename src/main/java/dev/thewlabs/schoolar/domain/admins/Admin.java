package dev.thewlabs.schoolar.domain.admins;

import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Admin extends User {
    
}
