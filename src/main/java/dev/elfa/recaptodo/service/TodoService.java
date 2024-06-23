package dev.elfa.recaptodo.service;

import dev.elfa.recaptodo.model.Todo;
import dev.elfa.recaptodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return this.todoRepository.findAll();
    }
}
