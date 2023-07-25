package com.hari.todo.dao;

import com.hari.todo.helper.Helper;
import com.hari.todo.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        Todo todo = new Todo();
        todo.setId((rs.getInt("id")));
        todo.setTitle(( rs.getString("title")));
        todo.setContent((rs.getString("content")));
        todo.setStatus(( rs.getString("status")));

        todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
        todo.setTodoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));


        return todo;
    }
}
