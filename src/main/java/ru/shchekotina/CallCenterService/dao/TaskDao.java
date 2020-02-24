package ru.shchekotina.CallCenterService.dao;

import ru.shchekotina.CallCenterService.model.Task;

import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks();
    void addTask(Task t);
    List<Task> getTaskByOrderNumber(int orderNumber);

}
