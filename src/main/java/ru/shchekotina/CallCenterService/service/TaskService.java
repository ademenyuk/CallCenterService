package ru.shchekotina.CallCenterService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.shchekotina.CallCenterService.dao.TaskDao;
import ru.shchekotina.CallCenterService.model.Task;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    @Qualifier("taskDaoPostgresqlImpl")
    private TaskDao taskDao;

    public void addTask(int orderNum) throws DataAccessException {
        taskDao.addTask(new Task(orderNum));
    }

    public List<Task> getAllTasks() throws DataAccessException {
        return taskDao.getAllTasks();
    }

    public List<Task> getTaskByOrderNumber(int orderNumber) throws DataAccessException {

        return taskDao.getTaskByOrderNumber(orderNumber);
    }
}
