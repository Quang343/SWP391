package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

    List<Role> findByStatusAndRoleNameIsNot(String status, String roleName);

    boolean existsByRoleNameIgnoreCase(String name);
    Role findByRoleID(long id);
}
