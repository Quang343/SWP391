package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.ForgotPassword;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword,Integer> {
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);

    @Modifying
    @Transactional
    @Query("delete from ForgotPassword f where f.user.userId = ?1")
    void deleteByUser(Long id);
}
