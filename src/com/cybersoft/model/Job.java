package com.cybersoft.model;

import java.util.Date;

public class Job {
    private int id;
    private String nameJob;
    private Date startDate;
    private Date endDate;
    private int userId;
    private int taskId;
    private int statusId;

    public Job() {
    }

    public Job(int id, String nameJob, Date startDate, Date endDate, int statusId, int userId, int taskId) {
        this.id = id;
        this.nameJob = nameJob;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.taskId = taskId;
        this.statusId = statusId;
    }

    public Job(String nameJob, Date startDate, Date endDate, int statusId, int userId, int taskId) {
        this.nameJob = nameJob;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.taskId = taskId;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String name) {
        this.nameJob = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}

