package com.example.AgriculturalWarehouseManagement.repositories;

import com.example.AgriculturalWarehouseManagement.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}
