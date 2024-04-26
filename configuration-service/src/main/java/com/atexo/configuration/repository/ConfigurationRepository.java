package com.atexo.configuration.repository;

import com.atexo.configuration.model.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ConfigurationRepository {
    private static final String CONFIGURATION_FILE_PATH = "configuration.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveConfiguration(Configuration configuration) throws IOException {
        saveConfigurationAsJsonFile(configuration);
    }

    public Configuration loadConfiguration() throws IOException {
        return loadConfigurationFromJsonFile();
    }

    private void saveConfigurationAsJsonFile(Configuration configuration) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(CONFIGURATION_FILE_PATH)) {
            objectMapper.writeValue(fileOutput, configuration);
        }
    }

    private Configuration loadConfigurationFromJsonFile() throws IOException {
        try (FileInputStream fileInput = new FileInputStream(CONFIGURATION_FILE_PATH)) {
            return objectMapper.readValue(fileInput, Configuration.class);
        }
    }
}
