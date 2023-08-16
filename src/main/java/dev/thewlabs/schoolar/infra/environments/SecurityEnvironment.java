package dev.thewlabs.schoolar.infra.environments;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

@Configurable
public class SecurityEnvironment {
    @Value("jwt.secret")
    public static String JWT_SECRET;
    public static String JWT_ISSUER;
    public static String JWT_AUDIENCES;
    public static Integer JWT_EXPIRATION_IN_HOURS;
    public static Integer JWT_REFRESH_EXPIRATION_IN_DAYS;
    private static Environment environment;

    @Autowired
    private SecurityEnvironment(@NotNull Environment environment) {
        SecurityEnvironment.JWT_EXPIRATION_IN_HOURS = environment.getProperty("jwt.expiration-in-hours", Integer.class);
        SecurityEnvironment.JWT_REFRESH_EXPIRATION_IN_DAYS = environment.getProperty("jwt.refresh.expiration-in-days", Integer.class);
    }
}
