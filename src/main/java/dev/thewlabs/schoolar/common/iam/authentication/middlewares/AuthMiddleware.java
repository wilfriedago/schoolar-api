package dev.thewlabs.schoolar.common.iam.authentication.middlewares;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authentication.models.Authorized;
import dev.thewlabs.schoolar.common.iam.authentication.services.AuthService;
import dev.thewlabs.schoolar.common.iam.authentication.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static dev.thewlabs.schoolar.shared.utils.StringUtils.notNullOrBlank;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@Slf4j
public class AuthMiddleware extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AuthService authService;

    @Autowired
    public AuthMiddleware(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String jwt = jwtService.extractToken(request);

        if (Boolean.TRUE.equals(notNullOrBlank(jwt))) {
            final String userEmail = jwtService.extractSubject(jwt);

            if (nonNull(userEmail) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
                Account userAccount = this.authService.loadAccountByEmail(userEmail);

                if (jwtService.isTokenValid(jwt, userAccount)) {
                    Authorized authorized = new Authorized(userAccount);

                    SecurityContextHolder.getContext().setAuthentication(authorized.getAuthentication(request));
                }
            }
        }


        filterChain.doFilter(request, response);
    }
}
