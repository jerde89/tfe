package com.tfe.fournil.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Additional resource web configuration.
 */
@Slf4j
@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        log.info("AddRessourceHandler charged. ");
        registry.addResourceHandler("/imageProduct/**").addResourceLocations("file:imageProduct/");
    }
}