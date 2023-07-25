package com.hari.todo.services.impl;

import com.hari.todo.exceptions.ResourceNotFoundException;
import com.hari.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class TodoServiceImpl implements com.hari.todo.services.TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    List<Todo> todos = new ArrayList<>();

    //create todo method
    public Todo createTodo(Todo todo) {
        //create..
        //change the logic
        todos.add(todo);
        logger.info("Todos {}", this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo1 = todos.stream().filter(todo -> todoId == todo.getId()).findAny().orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
        logger.info("TODO: {}", todo1);
        return todo1;

    }

    public Todo updateTodo(
            int todoId,
            Todo todoWithNewDetails) {
        List<Todo> newUpdatedList = todos.stream().map(todo -> {
            if (todo.getId() == todoId) {
                //perform update
                todo.setTitle(todoWithNewDetails.getTitle());
                todo.setContent(todoWithNewDetails.getContent());
                todo.setStatus(todoWithNewDetails.getStatus());

                return todo;
            } else {
                return todo;
            }
        }).collect(Collectors.toList());

        todos = newUpdatedList;
        todoWithNewDetails.setId(todoId);
        return todoWithNewDetails;
    }

    public void deleteTodo(int todoId) {
        logger.info("Delete todo");
        List<Todo> newList = todos.stream().filter(todo -> todo.getId() != todoId).collect(Collectors.toList());
        todos = newList;
    }
}
