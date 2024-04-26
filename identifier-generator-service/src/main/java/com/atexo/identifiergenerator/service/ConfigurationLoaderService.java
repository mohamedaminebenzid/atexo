package com.atexo.identifiergenerator.service;

import com.atexo.identifiergenerator.model.IdentifierGenerationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Configuration
public class ConfigurationLoaderService {
    private final RestClient.Builder restClientBuilder;

    public ConfigurationLoaderService(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    @Bean
    public IdentifierGenerationConfiguration identifierGenerationConfiguration() {
        return restClientBuilder.build().get().uri(URI.create("http://CONFIGURATION-SERVICE/v1/configurations")).retrieve().body(IdentifierGenerationConfiguration.class);

    }
}