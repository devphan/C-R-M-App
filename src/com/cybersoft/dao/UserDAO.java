package com.cybersoft.dao;

import com.cybersoft.connection.DBConnection;
import com.cybersoft.dto.UserDTO;
import com.cybersoft.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> findAll() {

        List<User> listUser = new ArrayList<User>();

        String query = "SELECT * FROM users";

        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));
                listUser.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUser;
    }

    //Phuong thuc tra ve DS UserDTO (hien thi role_name thay vi role_id)
    public List<UserDTO> findAllDTO() {
        String query = "SELECT u.id, u.email, u.fullname, r.description"
                + " FROM users u JOIN roles r ON u.role_id = r.id";

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            //Hien thu nhung Truong can thiet

            while (rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(rs.getInt("id"));
                userDTO.setFullname(rs.getString("fullname"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setRoleName(rs.getString("description"));

                userDTOList.add(userDTO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userDTOList;
    }


    public User findById(int id) {

        String query = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));
                System.out.println("Tim kiem thanh cong");
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public void insert(User user) {
        String query = "INSERT INTO users (fullname, email, password, avatar, role_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAvatar());
            statement.setInt(5, user.getRoleId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(User user) {
        String query = "UPDATE users SET fullname = ?, email = ?, password = ?, avatar = ?, role_id = ? WHERE id = ?" ;
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFullname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAvatar());
            statement.setInt(5, user.getRoleId());
            statement.setInt(6,user.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void remove(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Phuong thuc tim kiem Email de check Login
    public  User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        User user = new User();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }



}
