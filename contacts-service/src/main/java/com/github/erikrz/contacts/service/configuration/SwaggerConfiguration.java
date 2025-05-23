package com.github.erikrz.contacts.service.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Swagger config for Contacts Service.
 *
 * @author erikrz
 */
@EnableWebMvc
@Configuration
public class SwaggerConfiguration {

    protected String getApiTitle() {
        return "Contacts Service";
    }

    protected String getApiVersion() {
        return "0.0.1";
    }

    protected String getApiDescription() {
        return "Contacts Service Example, made with Java and Spring";
    }

    protected String getBasePackage() {
        return "com.github.erikrz.contacts.service.controller";
    }

    protected String getLicenseDisplayText() {
        return "Apache License Version 2.0";
    }

    protected String getLicenseUrl() {
        return "https://github.com/erikrz/java-restful-spring-workshop/blob/main/LICENSE";
    }

    protected String getTermsOfServiceUrl() {
        return "https://github.com/erikrz/java-restful-spring-workshop/blob/main/LICENSE";
    }

    protected String getContactName() {
        return "erikrz";
    }

    protected String getContactUrl() {
        return "https://github.com/erikrz";
    }

    protected String getContactEmail() {
        return "erikrz@live.com";
    }

    @Bean
    public GroupedOpenApi apiDocket() {
        return GroupedOpenApi.builder()
                .packagesToScan(getBasePackage())
                .group("contacts")
                .pathsToMatch("/rest-api/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(getApiInfo()))
                .build();
    }

    private Info getApiInfo() {
        return new Info()
                .title(this.getApiTitle())
                .description(this.getApiDescription())
                .version(this.getApiVersion())
                .contact(new Contact()
                        .email(this.getContactEmail())
                        .name(this.getContactName())
                        .url(this.getContactUrl()))
                .termsOfService(this.getTermsOfServiceUrl())
                .license(new License().name(this.getLicenseDisplayText()).url(this.getLicenseUrl()));
    }
}
