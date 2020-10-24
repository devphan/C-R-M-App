package com.cybersoft.dao;

import com.cybersoft.connection.DBConnection;
import com.cybersoft.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public List<Task> findAll() {
        List<Task> listTask = new ArrayList<Task>();

        String query = "SELECT * FROM tasks;";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("nameTask"));
                task.setStartDate(rs.getDate("startDate"));
                task.setEndDate(rs.getDate("endDate"));
                listTask.add(task);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return listTask;
    }

    public Task findById(int id) {

        Task task = new Task();

        String query = "SELECT * FROM tasks WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("nameTask"));
                task.setStartDate(rs.getDate("startDate"));
                task.setEndDate(rs.getDate("endDate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }

    public void insert(Task task) {
        String query = "INSERT INTO tasks (nameTask, startDate, endDate) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, task.getName());
            statement.setDate(2, (Date) task.getStartDate());
            statement.setDate(3, (Date) task.getEndDate());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(Task task) {
        String query = "UPDATE tasks SET nameTask = ?, startDate = ?, endDate = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, task.getName());
            statement.setDate(2, (Date) task.getStartDate());
            statement.setDate(3, (Date) task.getEndDate());
            statement.setInt(4, task.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void remove(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

