package com.atexo.identifiergenerator.service;

import com.atexo.identifiergenerator.model.IdentifierGenerationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class ConfigurationLoaderService {
    private final RestClient.Builder restClientBuilder;

    public ConfigurationLoaderService(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    public IdentifierGenerationConfiguration loadConfiguration() {
        return restClientBuilder.build().get().uri(URI.create("http://CONFIGURATION-SERVICE/v1/configurations")).retrieve().body(IdentifierGenerationConfiguration.class);

    }
}