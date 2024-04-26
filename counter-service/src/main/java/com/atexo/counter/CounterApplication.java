package com.atexo.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);
    }
    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
