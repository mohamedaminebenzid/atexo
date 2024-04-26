package com.atexo.identifiergenerator.controller;

import com.atexo.identifiergenerator.model.GeneratedIdentifier;
import com.atexo.identifiergenerator.model.Subscriber;
import com.atexo.identifiergenerator.service.IdentifierGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/identifiers")
public class IdentifierGeneratorController {
    private final IdentifierGeneratorService identifierGeneratorService;

    public IdentifierGeneratorController(IdentifierGeneratorService identifierGeneratorService) {
        this.identifierGeneratorService = identifierGeneratorService;
    }

    @Operation
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneratedIdentifier> generateIdentifier(@Valid @RequestBody Subscriber subscriber) throws NoSuchFieldException, IllegalAccessException {
        GeneratedIdentifier identifier = identifierGeneratorService.generateSubscriberIdentifier(subscriber);
        return ResponseEntity.ok(identifier);
    }


}
