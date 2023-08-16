package dev.thewlabs.schoolar.common.iam.authorization.entities;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.management.entities.Organization;
import dev.thewlabs.schoolar.core.abstracts.Traceable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@Setter
@Table(name = "permissions", schema = "v1")
public class Permission extends Traceable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Account> accounts;

    @ManyToMany(mappedBy = "permissions")
    private Set<Organization> organizations;
}
