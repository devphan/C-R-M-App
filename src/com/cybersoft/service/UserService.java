package com.cybersoft.service;

import com.cybersoft.dao.RoleDAO;
import com.cybersoft.dao.UserDAO;
import com.cybersoft.dto.UserDTO;
import com.cybersoft.model.Role;
import com.cybersoft.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAO();
    private RoleDAO roleDAO = new RoleDAO();

    //Ham tra ve ds JOIN 2 bang co Role id = Role name
    public List<UserDTO> getAllDTO() {
        return userDAO.findAllDTO();
    }

    public UserDTO getById(int id) {
        User user = userDAO.findById(id);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setEmail(user.getEmail());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setRoleId(user.getId());
        return userDTO;
    }
    //Ham add, edit truyen du lieu DTO sang Model


    public void add(UserDTO userDTO) {
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        user.setRoleId(userDTO.getRoleId());
        userDAO.insert(user);
    }

    public void edit(UserDTO userDTO) {

//        User user = userDAO.findById(userDTO.getId());
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        user.setRoleId(userDTO.getRoleId());
        user.setId(userDTO.getId());
        userDAO.update(user);
    }

    public void delete(int id) {
        userDAO.remove(id);
    }

    //Ham check Login
    public UserDTO checkLogin(String email, String password) {

        //Kiem tra xem Email dang nhap co trong DB ko?
        User user = userDAO.findByEmail(email);
        if (user == null) {
            return null;
        }

        //Check pass
        boolean checked = BCrypt.checkpw(password, user.getPassword());
        if(!checked) {
            return null;
        }

        //khởi tạo đối tượng Role để lấy RoleName gán vào trường RoleName của UserDTO bên dưới
        Role role = roleDAO.findById(user.getRoleId());

        //Khoi dao UserDTO de xu li phan quyen
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRoleName(role.getName());
        return userDTO;


    }


}
