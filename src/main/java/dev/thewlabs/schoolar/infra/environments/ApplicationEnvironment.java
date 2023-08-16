package dev.thewlabs.schoolar.infra.environments;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;

@Configurable
public class ApplicationEnvironment {
    public static String ACCESS_DENIED_URL;

    @Autowired
    private ApplicationEnvironment(@NotNull Environment environment) {
        ApplicationEnvironment.ACCESS_DENIED_URL = environment.getProperty("schoolar.frontend.access-denied-url");
    }
}
