package com.cybersoft.service;

import com.cybersoft.dao.JobDAO;
import com.cybersoft.dto.JobDTO;
import com.cybersoft.model.Job;

import java.util.ArrayList;
import java.util.List;

//Truyen du lieu va ham tu Tang DAO sang DTO
public class JobService {

    private JobDAO jobDAO = new JobDAO();


    public List<JobDTO> getAll() {
        // Khoi tao ListDTO de tra ve ds doi tuong DTO
        List<JobDTO> listJobDTO = new ArrayList<JobDTO>();

        //Truyen ham findAll(DAO) sang getALL(DTO)
        List<Job> listJob = jobDAO.findAll();

        //Chuyen du lieu tu model sang DTO
        for (Job job: listJob) {
            listJobDTO.add(new JobDTO(
                            job.getId(),
                            job.getNameJob(),
                            job.getStartDate(),
                            job.getEndDate(),
                            job.getStatusId(),
                            job.getUserId(),
                            job.getTaskId()
                    )
            );
        }

        return listJobDTO;
    }

    public JobDTO getById(int id) {

        //Truyen ham tu DAO sang DTO
        Job job = jobDAO.findById(id);

        //Chuyen du lieu tu model sang DTO
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setNameJob(job.getNameJob());
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setEndDate(job.getEndDate());
        jobDTO.setStatusId(job.getStatusId());
        jobDTO.setUserId(job.getUserId());
        jobDTO.setTaskId(job.getTaskId());
        return  jobDTO;
    }



    public void add(JobDTO jobDTO) {
        //Chuyen du lieu tu DTO sang Model
        Job job = new Job();
        job.setNameJob(jobDTO.getNameJob());
        job.setStartDate(jobDTO.getStartDate());
        job.setEndDate(jobDTO.getEndDate());
        job.setStatusId(jobDTO.getStatusId());
        job.setUserId(jobDTO.getUserId());
        job.setTaskId(jobDTO.getTaskId());
        //Truyen ham Insert cua DAO sang DTO
        jobDAO.insert(job);
    }

    public void edit(JobDTO jobDTO) {
        //Chuyen du lieu tu DTO sang Model
        Job job = new Job();
        job.setNameJob(jobDTO.getNameJob());
        job.setStartDate(jobDTO.getStartDate());
        job.setEndDate(jobDTO.getEndDate());
        job.setStatusId(jobDTO.getStatusId());
        job.setUserId(jobDTO.getUserId());
        job.setTaskId(jobDTO.getTaskId());
        job.setId(jobDTO.getId());

        jobDAO.update(job);
    }

    public void delete(int id) {
        jobDAO.remove(id);
    }




}

