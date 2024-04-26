package com.atexo.identifiergenerator.service;

import com.atexo.identifiergenerator.model.Counter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class CounterService {
    private final RestClient.Builder restClientBuilder;

    public CounterService(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    public int getIncrementedCounter() {
          return restClientBuilder.build().post().uri(URI.create("http://COUNTER-SERVICE/v1/counters")).retrieve().body(Counter.class).getValue();
    }
}
