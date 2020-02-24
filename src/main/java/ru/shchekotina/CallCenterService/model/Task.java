package ru.shchekotina.CallCenterService.model;

import java.util.Date;

public class Task {
    private long id;
    private int orderNum;
    private Date addingDate;

    public Task(int orderNum) {
        this.orderNum = orderNum;
    }

    public Task(long id, int orderNum, Date addingDate) {
        this.id = id;
        this.orderNum = orderNum;
        this.addingDate = addingDate;
    }

    public long getTaskId() {
        return id;
    }

    public void setTaskId(long taskId) {
        this.id = taskId;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    @Override
    public String toString() {
        return "Задание с id " + this.id
                + " по заказу № " + this.orderNum
                + ", созданное " + this.addingDate;
    }
}
