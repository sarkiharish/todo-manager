package com.hari.todo.services.impl;

import com.hari.todo.dao.TodoDao;
import com.hari.todo.models.Todo;
import com.hari.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class DaoTodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;
    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoDao.getTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        return todoDao.updateTodo(todoId, todoWithNewDetails);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
