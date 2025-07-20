package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    List<Role> findByStatusAndNameIsNot(String status, String name);

    boolean existsByNameIgnoreCase(String name);
    Role findById(long id);
}
