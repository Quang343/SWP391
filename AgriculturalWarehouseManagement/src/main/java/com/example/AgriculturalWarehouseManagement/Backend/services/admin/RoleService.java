package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.RoleDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.RoleRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> findByStatusAndNameIsNot(String status, String name) {
        return roleRepository.findByStatusAndRoleNameIsNot(status, name);
    }

    @Override
    public Role findById(Long id) throws Exception{
        return roleRepository.findById(id)
                .orElseThrow(() -> new Exception("Role not found!"));
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return roleRepository.existsByRoleNameIgnoreCase(name);
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = Role.builder()
                .roleName(roleDTO.getName())
                .status(roleDTO.getStatus())
                .description(roleDTO.getDescription())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleDTO roleDTO) {
        try {
            Role role = findById(id);
            role.setRoleName(roleDTO.getName());
            role.setDescription(roleDTO.getDescription());
//            role.setStatus(roleDTO.getStatus());
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRole(Long id) throws Exception {
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception("Role not found!"));
        List<User> users = userRepository.findByRole(role);
        if(!users.isEmpty()){
            throw new Exception("Không thể vô hiệu hóa role đang được sử dụng.");
        }
        role.setStatus("INACTIVE");
        roleRepository.save(role);
    }

    public void restoreRole(Long id) throws Exception {
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception("Role not found!"));
        role.setStatus("ACTIVE");
        roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }


}
