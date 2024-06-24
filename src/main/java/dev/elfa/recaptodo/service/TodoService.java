package dev.elfa.recaptodo.service;

import dev.elfa.recaptodo.dto.TodoDTO;
import dev.elfa.recaptodo.model.Todo;
import dev.elfa.recaptodo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final IdService idService;

    public List<Todo> getTodos() {
        return this.todoRepository.findAll();
    }

    public void setTodo(TodoDTO todoDTO) {
        Todo todo = new Todo(idService.generateUUID(), todoDTO.description(), todoDTO.status());
        this.todoRepository.save(todo);
    }

    public void updateTodo(String id, TodoDTO todoDTO) {
        Todo todo = new Todo(id, todoDTO.description(), todoDTO.status());
        this.todoRepository.save(todo);
    }
}
