package com.atexo.identifiergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class IdentifierGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentifierGeneratorApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
