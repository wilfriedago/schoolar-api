package dev.thewlabs.schoolar.common.iam.management.entities;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authorization.entities.Permission;
import dev.thewlabs.schoolar.common.iam.authorization.entities.Role;
import dev.thewlabs.schoolar.common.iam.management.enums.OrganizationStatus;
import dev.thewlabs.schoolar.core.abstracts.Traceable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@Setter
@Table(name = "organizations", schema = "v1")
public class Organization extends Traceable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "status", nullable = false)
    private OrganizationStatus status;

    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Account owner;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<OrganizationSuspension> suspensions;

    @ManyToMany
    @JoinTable(
            schema = "v1",
            name = "members",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Account> members;

    @ManyToMany
    @JoinTable(
            schema = "v1",
            name = "organization_roles",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            schema = "v1",
            name = "organization_permissions",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
}
