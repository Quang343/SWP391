package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.OrderDTO;
import com.example.AgriculturalWarehouseManagement.models.Order;
import com.example.AgriculturalWarehouseManagement.models.User;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO);

    Order findById(Long id);

    List<Order> findByUser(User user);

    List<Order> findAll();

    List<Order> findByStatusIsNot(String status);

    Order updateOrder(Long id, OrderDTO orderDTO);

    boolean deleteById(Long id);

    boolean updateOrderStatus(String status, Long id);

    boolean isExistsByOrderCode(String orderCode);
}
