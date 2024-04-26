package com.atexo.configuration.controller;

import com.atexo.configuration.model.Configuration;
import com.atexo.configuration.service.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/configurations")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    //Put instead of Post because the operation is idempotent
    @Operation
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateConfiguration(@Valid @RequestBody Configuration configuration) throws IOException {
        configurationService.saveConfiguration(configuration);
    }

    @Operation
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Configuration> getConfiguration() throws IOException {
        Configuration configuration = configurationService.loadConfiguration();
        return ResponseEntity.ok(configuration);
    }
}
