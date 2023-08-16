package dev.thewlabs.schoolar.infra.configs;

import dev.thewlabs.schoolar.common.iam.authentication.middlewares.AuthMiddleware;
import dev.thewlabs.schoolar.shared.http.HttpResponse;
import dev.thewlabs.schoolar.infra.environments.ApplicationEnvironment;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthMiddleware authMiddleware;

    @Autowired
    public SecurityConfig(AuthMiddleware authMiddleware) {
        this.authMiddleware = authMiddleware;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain csrfAndCorsFilter(@NotNull HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> {
                    try {
                        customizer.disable().exceptionHandling(configurer -> configurer.authenticationEntryPoint((req, res, exp) -> HttpResponse.forbidden(res)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .cors(customizer -> {
                    try {
                        customizer.disable().exceptionHandling(configurer -> configurer.authenticationEntryPoint((req, res, exp) -> HttpResponse.forbidden(res)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain apiFilter(@NotNull HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizer -> customizer.requestMatchers("/api/**").authenticated())
                .addFilterBefore(authMiddleware, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configurer -> configurer.accessDeniedPage(ApplicationEnvironment.ACCESS_DENIED_URL));

        return http.build();
    }
}
