package dev.elfa.recaptodo.dto;

import dev.elfa.recaptodo.model.TodoStatus;

public record TodoDTO(String description, TodoStatus status) {
}
