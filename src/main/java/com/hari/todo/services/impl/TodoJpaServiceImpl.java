package com.hari.todo.services.impl;

import com.hari.todo.dao.TodoRepository;
import com.hari.todo.exceptions.ResourceNotFoundException;
import com.hari.todo.models.Todo;
import com.hari.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TodoJpaServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoRepository.findById(todoId).orElseThrow(()-> new  ResourceNotFoundException("Todo with given id not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo with given id not found.", HttpStatus.NOT_FOUND));
        todo.setTitle(todoWithNewDetails.getTitle());
        todo.setContent(todoWithNewDetails.getContent());
        todo.setStatus(todoWithNewDetails.getStatus());
        todo.setTodoDate(todoWithNewDetails.getTodoDate());
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo with given id not found.", HttpStatus.NOT_FOUND));
        todoRepository.delete(todo);

    }
}
