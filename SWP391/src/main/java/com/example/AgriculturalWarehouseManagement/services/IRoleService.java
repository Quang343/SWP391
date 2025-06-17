package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.RoleDTO;
import com.example.AgriculturalWarehouseManagement.models.Role;

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
