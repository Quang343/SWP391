package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.RoleDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;

import java.util.List;

public interface IRoleService {

    Role createRole(RoleDTO roleDTO);
    Role updateRole(Long id, RoleDTO roleDTO);
    void deleteRole(Long id) throws Exception;

    List<Role> findAll();
    List<Role> findByStatusAndNameIsNot(String status, String name);
    Role findById(Long id) throws Exception;
    Role findByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
