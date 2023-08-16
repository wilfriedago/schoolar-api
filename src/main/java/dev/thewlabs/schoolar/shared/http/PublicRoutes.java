package dev.thewlabs.schoolar.shared.http;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.*;

@Slf4j
public class PublicRoutes {
    private final Map<HttpMethod, String[]> routes = new HashMap<>();
    private final List<AntPathRequestMatcher> matchers = new ArrayList<>();

    public PublicRoutes add(HttpMethod method, String... routes) {
        this.routes.put(method, routes);

        Arrays.asList(routes).forEach(route -> matchers.add(new AntPathRequestMatcher(route, method.name())));

        return this;
    }

    public boolean anyMatch(HttpServletRequest request) {
        try {
            return this.matchers.stream().anyMatch(requestMatcher -> requestMatcher.matches(request));
        } catch (Exception exception) {
            log.error("error on route matching", exception);
            return false;
        }
    }

    public void injectOn(HttpSecurity http) {
        routes.forEach((method, routeList) -> {
            try {
                http
                        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/**").permitAll())
                        .authorizeHttpRequests(authorize -> authorize.requestMatchers(method, routeList).permitAll());
            } catch (Exception exception) {
                log.error("error on set public routes", exception);
            }
        });
    }
}
