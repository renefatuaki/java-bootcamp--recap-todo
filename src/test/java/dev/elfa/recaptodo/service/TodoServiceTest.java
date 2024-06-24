package dev.elfa.recaptodo.service;

import dev.elfa.recaptodo.dto.TodoDTO;
import dev.elfa.recaptodo.model.Todo;
import dev.elfa.recaptodo.model.TodoStatus;
import dev.elfa.recaptodo.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    private final TodoRepository mockTodoRepo = mock(TodoRepository.class);
    private final IdService idService = mock(IdService.class);

    @Test
    void getTodos() {
        // Mocking data
        Todo todo1 = new Todo("1", "Buy groceries", TodoStatus.OPEN);
        Todo todo2 = new Todo("1", "Clean the house", TodoStatus.OPEN);
        List<Todo> todoList = List.of(todo1, todo2);
        when(mockTodoRepo.findAll()).thenReturn(todoList);

        // Service
        TodoService todoService = new TodoService(mockTodoRepo, idService);

        // Run test
        List<Todo> result = todoService.getTodos();

        // Check if method got called
        verify(mockTodoRepo).findAll();

        // Check if the method was called exactly once
        verify(mockTodoRepo, times(1)).findAll();

        // Check expected result
        assertEquals(todoList, result);
    }

    @Test
    void getTodo() {
        // Mocking data
        Todo todo = new Todo("1", "Buy groceries", TodoStatus.OPEN);
        when(mockTodoRepo.findById("1")).thenReturn(Optional.of(todo));

        // Service
        TodoService todoService = new TodoService(mockTodoRepo, idService);

        // Run test
        Optional<Todo> result = todoService.getTodo("1");

        // Check if method got called
        verify(mockTodoRepo).findById("1");

        // Check if the method was called exactly once
        verify(mockTodoRepo, times(1)).findById("1");

        // Check expected result
        assertEquals(Optional.of(todo), result);
    }

    @Test
    void setTodo() {
        // Mocking data
        String mockId = "1";
        when(idService.generateUUID()).thenReturn(mockId);
        TodoDTO todoDTO = new TodoDTO("Buy groceries", TodoStatus.OPEN);
        Todo todo = new Todo(idService.generateUUID(), todoDTO.description(), todoDTO.status());

        // Service
        TodoService todoService = new TodoService(mockTodoRepo, idService);

        // Run test
        todoService.setTodo(todoDTO);

        // Check if method got called
        verify(mockTodoRepo).save(todo);

        // Check if the method was called exactly once
        verify(mockTodoRepo, times(1)).save(todo);
    }

    @Test
    void updateTodo() {
        // Mocking data
        TodoDTO todoDTO = new TodoDTO("Buy groceries", TodoStatus.OPEN);
        Todo expectedTodo = new Todo("1", todoDTO.description(), todoDTO.status());
        when(mockTodoRepo.save(any(Todo.class))).thenReturn(expectedTodo);

        // Service
        TodoService todoService = new TodoService(mockTodoRepo, idService);

        // Run test
        todoService.updateTodo("1", todoDTO);

        // Check if method got called
        verify(mockTodoRepo).save(any(Todo.class));

        // Check if the method was called exactly once
        verify(mockTodoRepo, times(1)).save(any(Todo.class));
    }

    @Test
    void deleteTodo() {
        // Service
        TodoService todoService = new TodoService(mockTodoRepo, idService);

        // Run test
        todoService.deleteTodo("1");

        // Check if method got called
        verify(mockTodoRepo).deleteById("1");

        // Check if the method was called exactly once
        verify(mockTodoRepo, times(1)).deleteById("1");
    }
}