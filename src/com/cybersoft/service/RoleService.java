package com.cybersoft.service;

import com.cybersoft.dao.RoleDAO;
import com.cybersoft.dto.RoleDTO;
import com.cybersoft.model.Role;

import java.util.ArrayList;
import java.util.List;

//Truyen du lieu va ham tu Tang DAO sang DTO
public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();


    public List<RoleDTO> getAll() {
        // Khoi tao ListDTO de tra ve ds doi tuong DTO
        List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();

        //Truyen ham findAll(DAO) sang getALL(DTO)
        List<Role> listRole = roleDAO.findAll();

        //Chuyen du lieu tu model sang DTO
        for (Role role: listRole) {
            listRoleDTO.add(new RoleDTO(
                    role.getId(),
                    role.getName(),
                    role.getDescription()
                    )
            );
        }

        return listRoleDTO;
    }

    public RoleDTO getById(int id) {

        //Truyen ham tu DAO sang DTO
        Role role = roleDAO.findById(id);

        //Chuyen du lieu tu model sang DTO
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return  roleDTO;
    }



    public void add(RoleDTO roleDTO) {
        //Chuyen du lieu tu DTO sang Model
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());

        //Truyen ham Insert cua DAO sang DTO
        roleDAO.insert(role);
    }

    public void edit(RoleDTO roleDTO) {
        //Chuyen du lieu tu DTO sang Model
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());

        roleDAO.update(role);
    }

    public void delete(int id) {
        roleDAO.remove(id);
    }




}
