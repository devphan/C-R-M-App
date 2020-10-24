package com.cybersoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybersoft.connection.DBConnection;
import com.cybersoft.model.Role;

public class RoleDAO {
	
	
	public List<Role> findAll() {
		List<Role> listRole = new ArrayList<Role>();
		
		String query = "SELECT * FROM roles";
		
		try (Connection connection = DBConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);	
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				listRole.add(role);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listRole;
	}

	public Role findById(int id) {

		Role role = new Role();

		String query = "SELECT * FROM roles WHERE id = ?";

		try(Connection connection = DBConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return role;
	}
	
	public void insert(Role role) {
		String query = "INSERT INTO roles (name, description) VALUES (?, ?)";

		try (Connection connection = DBConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Role role) {
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?";

		try (Connection connection = DBConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void remove(int id) {
		String query = "DELETE FROM roles WHERE id = ?";

		try(Connection connection = DBConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

}
