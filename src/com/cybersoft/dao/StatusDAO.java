package com.cybersoft.dao;

import com.cybersoft.connection.DBConnection;
import com.cybersoft.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    public List<Status> findAll() {
        List<Status> listStatuses = new ArrayList<Status>();

        String query = "SELECT * FROM status;";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Status list = new Status(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                listStatuses.add(list);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return listStatuses;
    }

    public Status findById(int id) {

        Status status = new Status();

        String query = "SELECT * FROM status WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    public void insert(Status status) {
        String query = "INSERT INTO status name VALUES ?";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(Status status) {
        String query = "UPDATE status SET name = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status.getName());
            statement.setInt(2, status.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void remove(int id) {
        String query = "DELETE FROM status WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

