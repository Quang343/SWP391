package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerApplicationRepository extends JpaRepository<SellerApplication, Long> {
    List<SellerApplication> findByUser(User user);
    boolean existsByUserAndStatus(User user, SellerApplicationStatus status);

}
