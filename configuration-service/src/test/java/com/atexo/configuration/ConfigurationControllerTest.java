package com.atexo.configuration;

import com.atexo.configuration.model.Configuration;
import com.atexo.configuration.model.CriterionConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.TreeSet;

@SpringBootTest
@AutoConfigureMockMvc
public class ConfigurationControllerTest {
    //TODO Enrich the configuration object + assertions
    @Autowired
    private MockMvc mockMvc;

    private Configuration configuration;

    @BeforeAll
    public static void setup() {
        TreeSet<CriterionConfiguration> criteria = new TreeSet<>();
        Configuration configuration = new Configuration(10, criteria);
    }

    @Test
    public void testUpdateConfiguration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/configurations").contentType(MediaType.APPLICATION_JSON).content("{\"counterInitialValue\":\"10\"}")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetConfiguration() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/configurations").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
        // .andExpect(MockMvcResultMatchers.content().json("{\"counterInitialValue\":\"10\"}"));
    }

}
