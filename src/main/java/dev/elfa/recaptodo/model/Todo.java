package dev.elfa.recaptodo.model;

import org.springframework.data.annotation.Id;

public record Todo(@Id String id, String description, TodoStatus status) {
}
