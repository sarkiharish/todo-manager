package com.hari.todo.dao;

import com.hari.todo.helper.Helper;
import com.hari.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);
    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //create table if does not exists

        String createTable = "CREATE TABLE IF NOT EXISTS todos( id int primary key, title varchar(100) not null, content varchar(500) not null, status varchar(10) not null, addedDate datetime, todoDate datetime)";

        int update = template.update(createTable);

        logger.info("TODO Table created {} ", update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
    public Todo saveTodo(Todo todo) {

        String insertQuery = "INSERT INTO todos(id, title, content, status, addedDate, todoDate) VALUES(?, ?, ?, ?, ?, ?)";
        int update = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());

        logger.info("JDBC OPERATION: {} inserted", update);

        return todo;
    }


    //get single todo from database

//    public Todo getTodo(int id) {
//        String query = "SELECT * FROM todos WHERE id = ?";
//
//        Todo o = (Todo) template.queryForObject(query, new TodoRowMapper(), id);
//        return o;


//        Map<String, Object> todoData = template.queryForMap(query, id);
//        logger.info("TODO : {}", todoData);
//
//        Todo todo = new Todo();
//        todo.setId(((int)todoData.get("id")));
//        todo.setTitle(((String)todoData.get("title")));
//        todo.setContent(((String)todoData.get("content")));
//        todo.setStatus(((String)todoData.get("status")));
//
//        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setTodoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));
//
//        return todo;
//    }

    //GET TODO NEXT APPROACH i.e. Anonymous class o ROw mapper

    public Todo getTodo(int id) {
        String query = "SELECT * FROM todos WHERE id = ?";

        Todo todo = template.queryForObject(query, new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Todo todo = new Todo();
                todo.setId((rs.getInt("id")));
                todo.setTitle(( rs.getString("title")));
                todo.setContent((rs.getString("content")));
                todo.setStatus(( rs.getString("status")));

                todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                todo.setTodoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));


                return todo;
            }
        }, id);
        return todo;
    }

    //get all todo from database

//    public List<Todo> getAllTodos() {
//        String query = "SELECT * FROM todos";
//
//        List query1 = template.query(query, new TodoRowMapper());
//        return query1;


        //List<Map<String, Object>> maps = template.queryForList(query);

//        List<Todo> todos = maps.stream().map((map) -> {
//            Todo todo = new Todo();
//            todo.setId(((int) map.get("id")));
//            todo.setTitle(((String) map.get("title")));
//            todo.setContent(((String) map.get("content")));
//            todo.setStatus(((String) map.get("status")));
//
//            todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//            todo.setTodoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//
//            return todo;
//        }).collect(Collectors.toList());

//        return todos;

//    }


    //get all TODO next approach using rowmapper with Lambda

    public List<Todo> getAllTodos() {
        String query = "SELECT * FROM todos";

        List<Todo> todos = template.query(query, (rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId((rs.getInt("id")));
            todo.setTitle((rs.getString("title")));
            todo.setContent((rs.getString("content")));
            todo.setStatus((rs.getString("status")));

            todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
            todo.setTodoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));


            return todo;
        });

        return todos;
    }


        //update todo

    public Todo updateTodo(int id, Todo newTodo) {
        String query = "UPDATE todos SET title=?, content=?, status=?, addedDate=?, todoDate=? WHERE id=?";
        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getTodoDate(), id);
        logger.info("Updated : {}", update);
        newTodo.setId(id);
        return  newTodo;

    }

    //delete todo from database

    public void deleteTodo(int id) {
        String query = "DELETE FROM todos WHERE id=?";
        int update = template.update(query, id);
        logger.info("DELETED : {}", update);
    }

    public void deleteMultiple(int ids[]) {
        String query = "DELETE FROM todos WHERE id=?";

        int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for(int i: ints) {
            logger.info("DELETED {}", i);
        }
    }
}
