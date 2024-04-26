package com.atexo.counter.service;


import com.atexo.counter.model.CounterConfiguration;
import com.atexo.counter.repository.CounterRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@DependsOn({"counterRepository", "counterConfiguration"})
public class CounterService {
    private final CounterRepository counterRepository;

    private final CounterConfiguration counterConfiguration;

    public CounterService(CounterRepository counterRepository, CounterConfiguration counterConfiguration) {
        this.counterRepository = counterRepository;
        this.counterConfiguration = counterConfiguration;
    }

    @PostConstruct
    public void init() throws IOException {
        counterRepository.saveCounter(counterConfiguration.getCounterInitialValue());
    }

    //this is a basic implementation. we should implement a proper locking mechanism
    //in HA mode(multiple jvm)
    public synchronized int incrementCounter() throws IOException {
        int counterValue = counterRepository.loadCounter();

        counterValue++;

        counterRepository.saveCounter(counterValue);

        return counterValue;
    }


}


