package com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlogRepository extends JpaRepository<User, Integer> {
}
