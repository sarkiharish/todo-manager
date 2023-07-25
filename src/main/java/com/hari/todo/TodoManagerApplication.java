package com.hari.todo;

import com.hari.todo.dao.TodoDao;
import com.hari.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	@Autowired
	private TodoDao todoDao;

	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("APPlication started");
//		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("Template object : {} ", template );

//		Todo todo = new Todo();
//		todo.setId(1230);
//		todo.setTitle("Learn SpringBOOt");
//		todo.setContent("Learn Java and springboot for the new job as backend developer!");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//
//		todoDao.saveTodo(todo);
//
//		Todo todo = todoDao.getTodo(1230);
//		logger.info("TODO : {}", todo);

//		todo.setTitle("Learn Spring Boot Course");
//		todo.setContent("I have to learn Spring boot any how...");
//		todo.setStatus("DONE");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//		todoDao.updateTodo(1230, todo);

//		List<Todo> allTodos = todoDao.getAllTodos();
//		logger.info("ALL TODOS: {}", allTodos);

//		todoDao.deleteTodo(1230);

//		todoDao.deleteMultiple(new int[]{1,2});
	}
}
