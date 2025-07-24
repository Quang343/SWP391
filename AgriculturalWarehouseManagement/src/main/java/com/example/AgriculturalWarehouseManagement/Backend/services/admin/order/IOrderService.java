package com.example.AgriculturalWarehouseManagement.Backend.services.admin.order;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.OrderDTO_WareHouse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.domain.*;
import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO);

    List<Order> findByStatusOrder(String status);

    List<OrderDTO_WareHouse> findByStatus(String status);

    Order findById(Long id);

    List<Order> findByUser(User user);

    List<Order> findAll();

    List<Order> findByStatusIsNot(String status);

    Order updateOrder(Long id, OrderDTO orderDTO);

    boolean deleteById(Long id);

    boolean updateOrderStatus(String status, Long id);

    boolean isExistsByOrderCode(String orderCode);

    Page<Order> findAll(Pageable pageable);
}
