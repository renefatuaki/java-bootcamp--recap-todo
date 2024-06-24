package dev.elfa.recaptodo.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("BASE_URL", () -> mockWebServer.url("/").toString());
        registry.add("OPENAI_KEY", () -> "mock_openai_key");
    }

    @Test
    void setTodo() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                            "id": "chatcmpl-9dbzqkWpCIHZDQwBAMPNJYlRAiCsb",
                            "object": "chat.completion",
                            "created": 1719227878,
                            "model": "gpt-3.5-turbo-0125",
                            "choices": [
                                {
                                    "index": 0,
                                    "message": {
                                        "role": "assistant",
                                        "content": "Buying apples and bananas."
                                    },
                                    "logprobs": null,
                                    "finish_reason": "stop"
                                }
                            ],
                            "usage": {
                                "prompt_tokens": 20,
                                "completion_tokens": 5,
                                "total_tokens": 25
                            },
                            "system_fingerprint": null
                        }
                        """));

        mvc.perform(MockMvcRequestBuilders.post("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}