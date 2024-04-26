package com.atexo.configuration.service;

import com.atexo.configuration.model.Configuration;
import com.atexo.configuration.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public void saveConfiguration(Configuration configuration) throws IOException {
        configurationRepository.saveConfiguration(configuration);
    }

    public Configuration loadConfiguration() throws IOException {
        return configurationRepository.loadConfiguration();
    }
}
