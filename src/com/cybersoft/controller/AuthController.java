package com.cybersoft.controller;

import com.cybersoft.dto.UserDTO;
import com.cybersoft.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController  extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                req.getRequestDispatcher("/WEB-INF/views/home/login.jsp").forward(req, resp);
                break;
            case "/logout":
                //Xoa session
                HttpSession session = req.getSession();
                session.removeAttribute("user");

                //Chuyen ve trang Login
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        //Lay thong tin tu Form dang nhap trang Login
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //Goi ham checkLogin
        UserDTO userDTO = userService.checkLogin(email, password); // tra ve 1 doi tuong DTO

        //Khoi tao session de checkLogin va Phan quyen
        if(userDTO != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userDTO); //Gan gia tri cho Session chuyen qua cho Filter

            resp.sendRedirect(req.getContextPath() + "/home"); // dang nhap thanh cong
        }
        else {
            req.setAttribute("message", "Sai Email hoặc mật khẩu");
            req.getRequestDispatcher("/WEB-INF/views/home/login.jsp").forward(req, resp); // Chuyen huong lai ve trang Login
        }

    }
}
