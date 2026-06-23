package com.webservice.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// @WebMvcTest(helloController.class)
@SpringBootTest
@AutoConfigureMockMvc
class helloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHello() throws Exception {
        mockMvc
            .perform(get("/api/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, Spring Boot"));
    }

    @Test
    public void testGetGreeting() throws Exception {
        mockMvc
            .perform(get("/api/greeting"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hello, Spring Boot"))
            .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void testPostGreeting() throws Exception {
        String json = """
            {"message": "Hallo"}
                """;
        mockMvc
            .perform(
                post("/api/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hallo"))
            .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/")).andExpect(status().isOk());
    }
}
