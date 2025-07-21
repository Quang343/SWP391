package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Order_SellerRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByStatusIsNot(String status);
    boolean existsByOrderCode(String orderCode);
}
