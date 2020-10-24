package com.cybersoft.controller;

import com.cybersoft.dto.RoleDTO;
import com.cybersoft.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/role", "/role-add", "/role-edit", "/role-delete"})
public class RoleController extends HttpServlet {

    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/role":
                List<RoleDTO> roleDTOList = roleService.getAll();
                req.setAttribute("roleDTOs", roleDTOList);
                req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(name);
        roleDTO.setDescription(description);


        switch (action) {
            case "/role-add":
                roleService.add(roleDTO);
                resp.sendRedirect(req.getContextPath() + "/role");
                break;
            case "/role-edit":
                int id = Integer.parseInt(req.getParameter("id"));
                roleDTO.setId(id);
                roleService.edit(roleDTO);
                resp.sendRedirect(req.getContextPath() + "/role");
                break;
            case "/role-delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                roleService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/role");
                break;
            default:
                break;
        }


    }
}
