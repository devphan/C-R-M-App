package com.cybersoft.service;

import com.cybersoft.dao.StatusDAO;
import com.cybersoft.dto.StatusDTO;
import com.cybersoft.model.Status;

import java.util.ArrayList;
import java.util.List;

//Truyen du lieu va ham tu Tang DAO sang DTO
public class StatusService {

    private StatusDAO statusDAO = new StatusDAO();


    public List<StatusDTO> getAll() {
        // Khoi tao ListDTO de tra ve ds doi tuong DTO
        List<StatusDTO> listStatusDTO = new ArrayList<StatusDTO>();

        //Truyen ham findAll(DAO) sang getALL(DTO)
        List<Status> listStatus = statusDAO.findAll();

        //Chuyen du lieu tu model sang DTO
        for (Status status: listStatus) {
            listStatusDTO.add(new StatusDTO(
                            status.getId(),
                            status.getName()
                    )
            );
        }

        return listStatusDTO;
    }

    public StatusDTO getById(int id) {

        //Truyen ham tu DAO sang DTO
        Status status = statusDAO.findById(id);

        //Chuyen du lieu tu model sang DTO
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(status.getId());
        statusDTO.setName(status.getName());
        return  statusDTO;
    }



    public void add(StatusDTO statusDTO) {
        //Chuyen du lieu tu DTO sang Model
        Status status = new Status();
        status.setName(status.getName());

        //Truyen ham Insert cua DAO sang DTO
        statusDAO.insert(status);
    }

    public void edit(StatusDTO statusDTO) {
        //Chuyen du lieu tu DTO sang Model
        Status status = new Status();
        status.setId(statusDTO.getId());
        status.setName(status.getName());

        statusDAO.update(status);
    }

    public void delete(int id) {
        statusDAO.remove(id);
    }

}

