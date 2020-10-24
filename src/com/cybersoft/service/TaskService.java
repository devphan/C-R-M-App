package com.cybersoft.service;

import com.cybersoft.dao.TaskDAO;
import com.cybersoft.dto.TaskDTO;
import com.cybersoft.model.Task;

import java.util.ArrayList;
import java.util.List;

//Truyen du lieu va ham tu Tang DAO sang DTO
public class TaskService {

    private TaskDAO taskDAO = new TaskDAO();


    public List<TaskDTO> getAll() {
        // Khoi tao ListDTO de tra ve ds doi tuong DTO
        List<TaskDTO> listTaskDTO = new ArrayList<TaskDTO>();

        //Truyen ham findAll(DAO) sang getALL(DTO)
        List<Task> listTask = taskDAO.findAll();

        //Chuyen du lieu tu model sang DTO
        for (Task task: listTask) {
            listTaskDTO.add(new TaskDTO(
                            task.getId(),
                            task.getName(),
                            task.getStartDate(),
                            task.getEndDate()
                    )
            );
        }

        return listTaskDTO;
    }

    public TaskDTO getById(int id) {

        //Truyen ham tu DAO sang DTO
        Task task = taskDAO.findById(id);

        //Chuyen du lieu tu model sang DTO
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        return  taskDTO;
    }



    public void add(TaskDTO taskDTO) {
        //Chuyen du lieu tu DTO sang Model
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());

        //Truyen ham Insert cua DAO sang DTO
        taskDAO.insert(task);
    }

    public void edit(TaskDTO taskDTO) {
        //Chuyen du lieu tu DTO sang Model
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());

        taskDAO.update(task);
    }

    public void delete(int id) {
        taskDAO.remove(id);
    }

}

