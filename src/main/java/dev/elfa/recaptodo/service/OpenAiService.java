package dev.elfa.recaptodo.service;

import dev.elfa.recaptodo.model.OpenAiMessage;
import dev.elfa.recaptodo.model.OpenAiRequest;
import dev.elfa.recaptodo.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OpenAiService {

    private final RestClient client;

    public OpenAiService(
            @Value("${BASE_URL}") String baseUrl,
            @Value("${OPENAI_KEY}") String key
    ) {
        client = RestClient.builder()
                .defaultHeader("Authorization", "Bearer " + key)
                .baseUrl(baseUrl)
                .build();
    }

    public String getResponse(String q) {
        OpenAiRequest request = new OpenAiRequest(
                "gpt-3.5-turbo",
                List.of(new OpenAiMessage("user", "Correct spelling and grammar: " + q)),
                0.7);

        OpenAiResponse response = client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response != null ? response.getAnswer() : null;
    }
}
