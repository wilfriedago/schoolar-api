package dev.thewlabs.schoolar.common.iam.authentication.models;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authentication.enums.AccountStatus;
import dev.thewlabs.schoolar.common.iam.authorization.enums.RoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.util.Optional;
import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Authorized extends User {
    private final UUID id;

    private final String email;

    public Authorized(@NotNull Account account) {
        super(
                account.getEmail(),
                account.getPassword(),
                account.getStatus() == AccountStatus.ACTIVE,
                true,
                true,
                true,
                account.getAuthorities()
        );

        this.id = account.getId();
        this.email = account.getEmail();
    }

    public static Optional<Authorized> current() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (principal instanceof Authorized authorized) ? authorized : null);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this, null, getAuthorities());

        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return token;
    }

    public boolean isAdmin() {
        return getAuthorities().stream().anyMatch(role -> role.getAuthority().equals(RoleEnum.ADMIN));
    }

    public boolean itsMeOrIsAdmin(UUID id) {
        return isAdmin() || getId().equals(id);
    }
}
