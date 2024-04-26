package com.atexo.counter.service;


import com.atexo.counter.model.CounterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class ConfigurationLoaderService {
    @Bean
    public CounterConfiguration counterConfiguration(RestClient.Builder restClientBuilder) {
        return restClientBuilder.build().get().uri(URI.create("http://CONFIGURATION-SERVICE/v1/configurations")).retrieve().body(CounterConfiguration.class);

    }
}