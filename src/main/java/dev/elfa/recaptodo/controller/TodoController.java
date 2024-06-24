package dev.elfa.recaptodo.controller;

import dev.elfa.recaptodo.dto.TodoDTO;
import dev.elfa.recaptodo.model.Todo;
import dev.elfa.recaptodo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public List<Todo> getTodos() {
        return this.todoService.getTodos();
    }

    @PostMapping("/todo")
    public void setTodo(@RequestBody TodoDTO todoDTO) {
        this.todoService.setTodo(todoDTO);
    }

    @PutMapping("/todo/{id}")
    public void updateTodo(@PathVariable String id, @RequestBody TodoDTO todoDTO) {
        this.todoService.updateTodo(id, todoDTO);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable String id) {
        this.todoService.deleteTodo(id);
    }
}
