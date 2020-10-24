package com.cybersoft.controller;

import com.cybersoft.dto.TaskDTO;
import com.cybersoft.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/task", "/task-add", "/task-edit", "/task-delete"})
public class TaskController extends HttpServlet {

    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/task":
                List<TaskDTO> taskDTOList = taskService.getAll();
                req.setAttribute("taskDTOs", taskDTOList);
                req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();

        String nameTask = req.getParameter("name");

        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(nameTask);

        if (startDateString != null) {
            Date startDate = Date.valueOf(startDateString);
            taskDTO.setStartDate(startDate);
        }

        if (endDateString != null) {
            Date endDate = Date.valueOf(endDateString);
            taskDTO.setEndDate(endDate);
        }


        switch (action) {
            case "/task-add":
                taskService.add(taskDTO);
                resp.sendRedirect(req.getContextPath() + "/task");
                break;
            case "/task-edit":
                int id = Integer.parseInt(req.getParameter("id"));
                taskDTO.setId(id);
                taskService.edit(taskDTO);
                resp.sendRedirect(req.getContextPath() + "/task");
                break;
            case "/task-delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                taskService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/task");
                break;
            default:
                break;
        }

    }
}

