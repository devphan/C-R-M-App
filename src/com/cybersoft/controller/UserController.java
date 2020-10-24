package com.cybersoft.controller;


import com.cybersoft.dao.JobDAO;
import com.cybersoft.dao.UserDAO;
import com.cybersoft.dto.UserDTO;
import com.cybersoft.service.RoleService;
import com.cybersoft.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns = {"/user", "/user-add", "/user-edit", "/user-delete", "/user-details"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UserController extends HttpServlet {

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    private UserDAO userDAO = new UserDAO();
    private JobDAO jobDAO = new JobDAO();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();


        switch (action) {
            case "/user":
                List<UserDTO> userDTOList = userService.getAllDTO();
                req.setAttribute("userDTOs", userDTOList);

                //Truyen roleId cho Modal Add
                req.setAttribute("roleDTOs", roleService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
            break;
            case "/user-details":
                int idDetail = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("user", userService.getById(idDetail));
                req.setAttribute("profile", jobDAO.findInforUser(idDetail));
                req.getRequestDispatcher("/WEB-INF/views/user/details.jsp").forward(req, resp);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String avatar = req.getParameter("avatar");

        switch (action) {

            case "/user-add":
                int roleId = Integer.parseInt(req.getParameter("roleId"));
                UserDTO userDTO = new UserDTO(fullname, email, password, avatar, roleId);
                //Ma hoa Mat khau
                String hashed = BCrypt.hashpw(password,BCrypt.gensalt(12));
                userDTO.setPassword(hashed);

                userService.add(userDTO);
                resp.sendRedirect(req.getContextPath() + "/user");
                break;
            case "/user-edit":
                int id = Integer.parseInt(req.getParameter("id"));

                int roleIdEdit = Integer.parseInt(req.getParameter("roleIdEdit"));

                UserDTO userDTOEdit = new UserDTO();

                userDTOEdit.setId(id);
                userDTOEdit.setFullname(fullname);
                userDTOEdit.setEmail(email);
                String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(12));
                userDTOEdit.setPassword(hashedPassword);
                userDTOEdit.setAvatar(avatar);
                userDTOEdit.setRoleId(roleIdEdit);
                userService.edit(userDTOEdit);
                resp.sendRedirect(req.getContextPath() + "/user");
                break;
            case "/user-delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                userService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/user");
                break;

            default:

        }
    }

    private boolean uploadFile(HttpServletRequest req){
        try {
            for (Part part : req.getParts()) {
                String contentDisp = part.getHeader("content-dispositon");
                String[] items = contentDisp.split(";");

                String fileName = "";
                for (String s : items) {
                    if (s.trim().startsWith("filename")) {
                        fileName = s.substring(s.indexOf("=") + 2, s.length()  - 1 );
                    }
                }
                if (!fileName.isEmpty()) {
                    File folderUpload = new File(System.getProperty("user.home") +   "/Uploads");
                    if (!folderUpload.exists()) {
                        folderUpload.mkdirs();
                    }
                    part.write(folderUpload.getAbsolutePath() + File.separator + fileName);
                }
            }
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
