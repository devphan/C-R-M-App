package com.cybersoft.dao;

import com.cybersoft.connection.DBConnection;
import com.cybersoft.dto.JobDTO;
import com.cybersoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    public List<Job> findAll() {

        List<Job> listJob = new ArrayList<Job>();

        String query = "SELECT * FROM jobs";

        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setNameJob(rs.getString("nameJob"));
                job.setStartDate(rs.getDate("startDate"));
                job.setEndDate(rs.getDate("endDate"));
                job.setUserId(rs.getInt("user_id"));
                job.setTaskId(rs.getInt("task_id"));
                job.setStatusId(rs.getInt("status_id"));
                listJob.add(job);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listJob;
    }

    
    public List<JobDTO> findAllDTO() {
        String query = "SELECT j.id, j.nameJob, j.startDate, j.endDate, s.name, u.fullname, t.nameTask"
                + " FROM jobs j JOIN status s ON j.status_id = s.id"
                + " JOIN users u ON j.user_id = u.id"
                + " JOIN tasks t ON j.task_id = t.id";

        List<JobDTO> jobDTOList = new ArrayList<JobDTO>();

        try(Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            //Hien thu nhung Truong can thiet

            while (rs.next()) {
                JobDTO jobDTO = new JobDTO();
                jobDTO.setId(rs.getInt("id"));
                jobDTO.setNameJob(rs.getString("nameJob"));
                jobDTO.setStartDate(rs.getDate("startDate"));
                jobDTO.setEndDate(rs.getDate("endDate"));
                jobDTO.setStatusName(rs.getString("name"));
                jobDTO.setUserFullName(rs.getString("fullname"));
                jobDTO.setTaskName(rs.getString("nameTask"));
                jobDTOList.add(jobDTO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return jobDTOList;
    }

    //Phương thức đổ lữ liệu cho trang profile
    public List<JobDTO> findInforUser(int id) {
        String query = "SELECT j.id, j.nameJob, t.nameTask, j.startDate, j.endDate, s.name FROM jobs j " +
                "JOIN status s ON j.status_id = s.id JOIN users u ON j.user_id = u.id " +
                "JOIN tasks t ON j.task_id = t.id where j.user_id = ? ;";

        List<JobDTO> jobDTOList = new ArrayList<JobDTO>();

        try(Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                JobDTO jobDTO = new JobDTO();
                jobDTO.setId(rs.getInt("id"));
                jobDTO.setNameJob(rs.getString("nameJob"));
                jobDTO.setStartDate(rs.getDate("startDate"));
                jobDTO.setEndDate(rs.getDate("endDate"));
                jobDTO.setStatusName(rs.getString("name"));
                jobDTO.setTaskName(rs.getString("nameTask"));
                jobDTOList.add(jobDTO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return jobDTOList;
    }


    public Job findById(int id) {

        String query = "SELECT * FROM jobs WHERE id = ?";
        Job job = new Job();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                job.setId(rs.getInt("id"));
                job.setNameJob(rs.getString("nameJob"));
                job.setStartDate(rs.getDate("startDate"));
                job.setEndDate(rs.getDate("endDate"));
                job.setStatusId(rs.getInt("status_id"));
                job.setUserId(rs.getInt("user_id"));
                job.setTaskId(rs.getInt("task_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return job;
    }

    public void insert(Job job) {
        String query = "INSERT INTO jobs (nameJob, startDate, endDate, status_id, user_id, task_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, job.getNameJob());
            statement.setDate(2, (Date) job.getStartDate());
            statement.setDate(3, (Date) job.getEndDate());
            statement.setInt(4, job.getStatusId());
            statement.setInt(5, job.getUserId());
            statement.setInt(6, job.getTaskId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Job job) {
        String query = "UPDATE jobs SET nameJob = ?, startDate = ?, endDate = ?, status_id = ?, user_id = ?, task_id = ? WHERE id = ?" ;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, job.getNameJob());
            statement.setDate(2, (Date) job.getStartDate());
            statement.setDate(3, (Date) job.getEndDate());
            statement.setInt(4, job.getStatusId());
            statement.setInt(5, job.getUserId());
            statement.setInt(6, job.getTaskId());
            statement.setInt(7,job.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(int id) {
        String query = "DELETE FROM jobs WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
