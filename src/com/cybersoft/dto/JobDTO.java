package com.cybersoft.dto;

import java.util.Date;

public class JobDTO {
    private int id;
    private String nameJob;
    private Date startDate;
    private Date endDate;
    private int userId;
    private int taskId;
    private int statusId;
    private String userFullName;
    private String taskName;
    private String statusName;

    public JobDTO() {
    }

    public JobDTO(int id, String nameJob, Date startDate, Date endDate, int statusId, int userId, int taskId) {
        this.id = id;
        this.nameJob = nameJob;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.taskId = taskId;
        this.statusId = statusId;
    }

    public JobDTO(String nameJob, Date startDate, Date endDate, int statusId, int userId, int taskId) {
        this.nameJob = nameJob;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.taskId = statusId;
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

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
