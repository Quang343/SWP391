package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    boolean existsByName(String name);
    Role findById(long id);
}
