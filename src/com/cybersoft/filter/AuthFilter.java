package com.cybersoft.filter;

import com.cybersoft.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home", "/role", "/user", "/task", "/job"})
public class AuthFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Ep kieu sang HttpServlet de chuyen huong trang
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String action = req.getServletPath();

        //Neu la trang Login thi ko can Check
        if (action.startsWith("/login")) {
            chain.doFilter(request, response); // de cho Request di qua
            return; // dung ham
        }

        //Check Session Dang nhap
        HttpSession session = req.getSession();

        //lay "user" o AuthController
        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        //Lay thong tin User luu trong Session
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        String roleName = userDTO.getRoleName();



        //PHAN QUYEN

        //TH1: Muon vao /role thi phai co roleName la ADMIN
        if (action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        //TH2: Muon vao /task thi phai co roleName la ADMIN or LEADER
        if (action.startsWith("/task") && !roleName.equals("ROLE_ADMIN") && !roleName.equals("ROLE_LEADER")) {
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        //TH3: Muon vao /job thi phai co roleName la ADMIN
        if (action.startsWith("/job") && !roleName.equals("ROLE_ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        //TH4: Muon vao /user thi phai co roleName la ADMIN hoac LEADER

        if (action.startsWith("/user") && !roleName.equals("ROLE_ADMIN") && !roleName.equals("ROLE_LEADER")) {
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        //TH5: Muon vao /user thi phai co roleName la ADMIN hoac LEADER

        if (action.startsWith("/profile") && !roleName.equals("ROLE_ADMIN") && !roleName.equals("ROLE_LEADER")) {
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        //TH6 : xem và sửa thông tin cá nhân
        if (action.equals("/profile") || action.equals("/profile-edit") && !roleName.equals("ROLE_MEMBER")){
            resp.sendRedirect(req.getContextPath() + "/error-403");
            return;
        }

        chain.doFilter(request, response);//de request di tiep

    }

    @Override
    public void destroy() {

    }
}
