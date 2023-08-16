package dev.thewlabs.schoolar.infra.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {
    private String username;
    private String password;
    private String appName;
    private String appDescription;
    private String appVersion;
    private String appLicense;
    private String appLicenseUrl;
    private String contactName;
    private String contactUrl;
    private String contactMail;
    private String devUrl;
    private String devDescription;
    private String prodUrl;
    private String prodDescription;

    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(devDescription);

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription(prodDescription);

        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setEmail(contactMail);
        contact.setUrl(contactUrl);

        License mitLicense = new License().name(appLicense).url(appLicenseUrl);

        Info info = new Info()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .contact(contact)
                .license(mitLicense);

        Components components = new Components().addSecuritySchemes("BearerAuth", new SecurityScheme()
                .bearerFormat("JWT")
                .scheme("bearer")
                .type(HTTP)
                .in(HEADER));

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer)).components(components);
    }
}
