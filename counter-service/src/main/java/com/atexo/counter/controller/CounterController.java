package com.atexo.counter.controller;

import com.atexo.counter.model.Counter;
import com.atexo.counter.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/counters")
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @Operation
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Counter> incrementCounter() throws IOException {
        int counterValue = counterService.incrementCounter();
        return ResponseEntity.ok(new Counter(counterValue));
    }
}
