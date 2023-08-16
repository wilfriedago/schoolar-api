package dev.thewlabs.schoolar.common.iam.authorization.entities;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.management.entities.Organization;
import dev.thewlabs.schoolar.core.abstracts.Traceable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = false)
public class Role extends Traceable implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts;

    @ManyToMany(mappedBy = "roles")
    private List<Organization> organizations;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        return null;
    }
}
