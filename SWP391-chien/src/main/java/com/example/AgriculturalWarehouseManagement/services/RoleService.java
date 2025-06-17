package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.RoleDTO;
import com.example.AgriculturalWarehouseManagement.models.Role;
import com.example.AgriculturalWarehouseManagement.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long id) throws Exception{
        return roleRepository.findById(id)
                .orElseThrow(() -> new Exception("Role not found!"));
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = Role.builder()
                .name(roleDTO.getName())
                .status(roleDTO.getStatus())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleDTO roleDTO) {
        try {
            Role role = findById(id);
            role.setName(roleDTO.getName());
            role.setDescription(roleDTO.getDescription());
            role.setStatus(roleDTO.getStatus());
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRole(Long id) {
        try {
            Role role = findById(id);
            role.setStatus("INACTIVE");
            roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }
}
