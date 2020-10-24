package com.cybersoft.controller;

import com.cybersoft.dao.JobDAO;
import com.cybersoft.dto.JobDTO;
import com.cybersoft.service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/job", "/job-add", "/job-details", "/job-edit", "/job-delete"})
public class JobController extends HttpServlet {
    private JobService jobService = new JobService();
    private StatusService statusService = new StatusService();
    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();

    private JobDAO jobDAO = new JobDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/job":
                List<JobDTO> jobDTOList = jobDAO.findAllDTO();
                req.setAttribute("jobDTOs", jobDTOList);

                //Truyen nameTask, Fullname của User và Status
                req.setAttribute("taskDTOs", taskService.getAll());
                req.setAttribute("userDTOs", userService.getAllDTO());
                req.setAttribute("statusDTOs", statusService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/job/index.jsp").forward(req, resp);
                break;
//            case "/job-details":
//                req.getRequestDispatcher("/WEB-INF/views/job/details.jsp").forward(req, resp);
//                break;

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        String nameJob = req.getParameter("nameJob");
        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");
        String statusIdString = req.getParameter("statusId");
        String userIdString = req.getParameter("userId");
        String taskIdString = req.getParameter("taskId");

        JobDTO jobDTO = new JobDTO();
        jobDTO.setNameJob(nameJob);

        if (startDateString != null) {
            Date startDate = java.sql.Date.valueOf(startDateString);
            jobDTO.setStartDate(startDate);
        }

        if (endDateString != null) {
            Date endDate = Date.valueOf(endDateString);
            jobDTO.setEndDate(endDate);
        }

        if (statusIdString != null) {
            int statusId = Integer.valueOf(statusIdString);
            jobDTO.setStatusId(statusId);
        }

        if (userIdString != null) {
            int userId = Integer.valueOf(userIdString);
            jobDTO.setUserId(userId);
        }

        if (taskIdString != null) {
            int taskId = Integer.valueOf(taskIdString);
            jobDTO.setTaskId(taskId);
        }


        switch (action) {
            case "/job-add":
                jobService.add(jobDTO);
                resp.sendRedirect(req.getContextPath() + "/job");
                break;
            case "/job-edit":
                int id = Integer.parseInt(req.getParameter("id"));
                jobDTO.setId(id);
                jobService.edit(jobDTO);
                resp.sendRedirect(req.getContextPath() + "/job");
                break;
            case "/job-delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                jobService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/job");
                break;
            default:

        }
    }


}
