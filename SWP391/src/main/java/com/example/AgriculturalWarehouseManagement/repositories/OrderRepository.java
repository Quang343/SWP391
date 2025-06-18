package com.example.AgriculturalWarehouseManagement.repositories;

import com.example.AgriculturalWarehouseManagement.models.Order;
import com.example.AgriculturalWarehouseManagement.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByStatusIsNot(String status);

    boolean existsByOrderCode(String orderCode);
}
