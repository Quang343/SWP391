package com.example.AgriculturalWarehouseManagement.Backend.repositorys.shipper;

import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@author: Đào Huy Hoàng

@Repository
public interface OrderShipperRepository extends JpaRepository<Order, Long> {
   // List<Order> findByStatus(OrderStatus status);

    List<Order> findByStatus(String status);
}
