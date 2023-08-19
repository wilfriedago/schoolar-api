package dev.thewlabs.schoolar.common.iam.authentication.entities;

import dev.thewlabs.schoolar.common.iam.authentication.enums.AccountStatus;
import dev.thewlabs.schoolar.common.iam.authorization.entities.Permission;
import dev.thewlabs.schoolar.common.iam.authorization.entities.Role;
import dev.thewlabs.schoolar.common.iam.management.entities.Organization;
import dev.thewlabs.schoolar.core.abstracts.Traceable;
import dev.thewlabs.schoolar.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = false)
public class Account extends Traceable implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.INACTIVE;

    private String avatarUrl = "https://defaultavatar.url/";

    private boolean emailVerified = false;

    @OneToOne(mappedBy = "account", optional = false)
    private User user;

    @OneToOne(mappedBy = "owner")
    private Organization organization;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<RefreshToken> refreshTokens;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<PasswordResetToken> passwordResetTokens;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<EmailVerificationToken> emailVerificationTokens;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountSuspension> suspensions;

    @ManyToMany(mappedBy = "members")
    private List<Organization> organizations;

    @ManyToMany
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "account_permissions",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
