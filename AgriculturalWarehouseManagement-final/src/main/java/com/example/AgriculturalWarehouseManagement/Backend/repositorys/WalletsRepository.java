package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletsRepository extends JpaRepository<Wallets, Long> {
    Optional<Wallets> findByUser(User user);
}
