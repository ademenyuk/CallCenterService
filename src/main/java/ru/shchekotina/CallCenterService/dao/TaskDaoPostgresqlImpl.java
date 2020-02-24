package ru.shchekotina.CallCenterService.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.shchekotina.CallCenterService.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class TaskDaoPostgresqlImpl implements TaskDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> getAllTasks() throws DataAccessException {
        List<Task> tasks = jdbcTemplate.query("select * from tasks order by date desc", new Object[] {}, new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Task(rs.getLong("id"), rs.getInt("orderNumber"), new Date(rs.getTimestamp("date").getTime()));
            }
        });
        return tasks;
    }

    @Override
    public void addTask(Task t) throws DataAccessException {
        jdbcTemplate.update("INSERT INTO tasks (orderNumber) values(?)", t.getOrderNum());
    }

    @Override
    public List<Task> getTaskByOrderNumber(int orderNumber) throws DataAccessException {
        List<Task> tasks = jdbcTemplate.query("select * from tasks where tasks.orderNumber = ? order by date desc", new Object[] {orderNumber}, new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Task(rs.getLong("id"), rs.getInt("orderNumber"), new Date(rs.getTimestamp("date").getTime()));
            }
        });
        return tasks;
    }
}
