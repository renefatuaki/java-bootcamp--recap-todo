package dev.elfa.recaptodo.model;

import java.util.List;

public record OpenAiResponse(List<OpenAiChoice> choices) {
    public String getAnswer() {
        return choices.getFirst().message().content();
    }
}
