package com.cybersoft.controller;

import com.cybersoft.dao.JobDAO;
import com.cybersoft.dto.UserDTO;
import com.cybersoft.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "/profile"})
public class HomeController extends HttpServlet {
    private JobDAO jobDAO = new JobDAO();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/home":
                req.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(req, resp);
                break;
            case "/profile":
                HttpSession httpSession = req.getSession();
                UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
                req.setAttribute("user", userService.getById(userDTO.getId()));
                req.setAttribute("profile", jobDAO.findInforUser(userDTO.getId()));
                req.getRequestDispatcher("/WEB-INF/views/home/profile.jsp").forward(req, resp);
                break;
        }
    }
}
