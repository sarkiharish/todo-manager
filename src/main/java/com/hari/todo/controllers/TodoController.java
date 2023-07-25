package com.hari.todo.controllers;

import com.hari.todo.models.Todo;
import com.hari.todo.services.TodoService;
import com.hari.todo.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    Random random = new Random(9999999);

    //create
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
        //create todo
//        String str = null;
//        System.out.println(str.length());

        int id = random.nextInt(9999999);
        todo.setId(id);

        //create date with system default current date
        Date currentDate = new Date();
        logger.info("Current date {}", currentDate);
        todo.setAddedDate(currentDate);

        logger.info("Create Todo");

        //call service to create todo
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);


    }


    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler() {
        List<Todo> allTodos = todoService.getAllTodos();
         return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    //get single todo

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }


    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(
            @RequestBody Todo todoWithNewDetails,
            @PathVariable int todoId) {
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    //delete todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Successfully deleted!");
    }


    //exception handler : Controller specific
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex) {
//        System.out.println(ex.getMessage());
//        System.out.println("Null pointer exception generated.");
//        return new ResponseEntity<>("Null Pointer exception: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
