package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.RoleDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;

import java.util.List;

public interface IRoleService {

    Role createRole(RoleDTO roleDTO);
    Role updateRole(Long id, RoleDTO roleDTO);
    void deleteRole(Long id);

    List<Role> findAll();
    Role findById(Long id) throws Exception;
    Role findByName(String name);
    boolean existsByName(String name);
}
