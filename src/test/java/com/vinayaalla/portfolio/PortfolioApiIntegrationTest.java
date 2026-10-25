package com.vinayaalla.portfolio;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vinayaalla.portfolio.contact.ContactMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactMessageRepository messages;

    @BeforeEach
    void clearMessages() {
        messages.deleteAll();
    }

    @Test
    void returnsProjectDataFromTheBackend() throws Exception {
        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("agriculture"))
                .andExpect(jsonPath("$[0].technologies[4]").value("Spring Boot"));
    }

    @Test
    void servesThePortfolioFrontend() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
            .andExpect(forwardedUrl("index.html"));
    }

    @Test
    void persistsValidContactMessages() throws Exception {
        String request = """
            {"name":"Vinay Visitor","email":"visitor@example.com","message":"Let's talk about a project."}
            """;

        mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.status").value("Message received"));

        org.assertj.core.api.Assertions.assertThat(messages.count()).isEqualTo(1);
    }

    @Test
    void rejectsInvalidContactMessages() throws Exception {
        String request = """
            {"name":"Visitor","email":"not-an-email","message":""}
            """;

        mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest());

        org.assertj.core.api.Assertions.assertThat(messages.count()).isZero();
    }
}