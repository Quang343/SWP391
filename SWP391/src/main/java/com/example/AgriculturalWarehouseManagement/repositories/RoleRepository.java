package com.example.AgriculturalWarehouseManagement.repositories;

import com.example.AgriculturalWarehouseManagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    boolean existsByName(String name);
    Role findById(long id);
}
