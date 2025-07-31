package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SellerApplicationRepository extends JpaRepository<SellerApplication, Long> {
    List<SellerApplication> findByUser(User user);
    boolean existsByUserAndStatus(User user, SellerApplicationStatus status);
    Optional<SellerApplication> findByIdAndUserAndStatusIn(Long id, User user, List<SellerApplicationStatus> statuses);
    Optional<Object> findByIdAndUserAndStatus(Long id, User user, SellerApplicationStatus status);
    @Query("SELECT sa FROM SellerApplication sa WHERE sa.status = 'APPROVED'")
    List<SellerApplication> findAllApprovedApplications();

    Optional<SellerApplication> findById(Long id);

}
