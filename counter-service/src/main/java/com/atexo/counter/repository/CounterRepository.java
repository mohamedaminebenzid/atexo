package com.atexo.counter.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class CounterRepository {
    private static final String COUNTER_STORAGE_PATH = "counter.json";

    private final ObjectMapper mapper = new ObjectMapper();

    public void saveCounter(int counter) throws IOException {
        Path filePath = Path.of(COUNTER_STORAGE_PATH);
        JsonNode rootNode = mapper.createObjectNode().put("value", counter);
        mapper.writeValue(filePath.toFile(), rootNode);
    }

    public int loadCounter() throws IOException {
        Path filePath = Path.of(COUNTER_STORAGE_PATH);
        JsonNode rootNode = mapper.readTree(filePath.toFile());
        return rootNode.get("value").asInt();
    }
}
