package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = Order.builder()
                .user(user)
                .orderDate(orderDTO.getOrderDate())
                .status(orderDTO.getStatus())
                .totalAmount(orderDTO.getTotalAmount())
                .fullName(orderDTO.getFullName())
                .email(orderDTO.getEmail())
                .phone(orderDTO.getPhone())
                .address(orderDTO.getAddress())
                .note(orderDTO.getNote())
                .orderCode(generateUniqueOrderCode())
                .build();
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatusIsNot(String status) {
        return orderRepository.findByStatusIsNot(status);
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setStatus(OrderStatus.REMOVED.name());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrderStatus(String status, Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistsByOrderCode(String orderCode) {
        return orderRepository.existsByOrderCode(orderCode);
    }

    public String generateUniqueOrderCode() {
        String code;
        do {
            int randomNumber = 1000000 + random.nextInt(9000000); // 7 chữ số
            code = String.format("#ORD%d", randomNumber);
        } while (orderRepository.existsByOrderCode(code));
        return code;
    }

    @Override
    public List<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
