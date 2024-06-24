package dev.elfa.recaptodo.controller;

import dev.elfa.recaptodo.dto.TodoDTO;
import dev.elfa.recaptodo.model.Todo;
import dev.elfa.recaptodo.service.OpenAiService;
import dev.elfa.recaptodo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;
    private final OpenAiService openAiService;

    public TodoController(TodoService todoService, OpenAiService openAiService) {
        this.todoService = todoService;
        this.openAiService = openAiService;
    }

    @GetMapping("/todo")
    public List<Todo> getTodos() {
        return this.todoService.getTodos();
    }

    @GetMapping("/todo/{id}")
    public Optional<Todo> getTodo(@PathVariable String id) {
        return this.todoService.getTodo(id);
    }

    @PostMapping("/todo")
    public void setTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO todoDTOAutoCorrect = new TodoDTO(openAiService.getResponse(todoDTO.description()), todoDTO.status());
        this.todoService.setTodo(todoDTOAutoCorrect);
    }

    @PutMapping("/todo/{id}")
    public void updateTodo(@PathVariable String id, @RequestBody TodoDTO todoDTO) {
        TodoDTO todoDTOAutoCorrect = new TodoDTO(openAiService.getResponse(todoDTO.description()), todoDTO.status());
        this.todoService.updateTodo(id, todoDTOAutoCorrect);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable String id) {
        this.todoService.deleteTodo(id);
    }
}
