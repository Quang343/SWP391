package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;

import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.Order_SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Order_SellerServiceImpl implements Order_SellerService {

    @Autowired
    private Order_SellerRepository order_SellerRepository;

    @Override
    public List<Order_SellerDTO> getAllOrders_Seller() {
        List<Order> orders = order_SellerRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Order_SellerDTO convertToDTO(Order order) {
        Order_SellerDTO dto = new Order_SellerDTO();
        dto.setOrderId(order.getId());
        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(order.getOrderDate());
        dto.setPhone(order.getPhone());
        dto.setAddress(order.getAddress());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setFinalAmount(order.getFinalAmount());
        dto.setStatus(order.getStatus());
        return dto;
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = order_SellerRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("PENDING".equals(order.getStatus())) {
            order.setStatus("CONFIRMED");
            order_SellerRepository.save(order);
        } else {
            throw new RuntimeException("Only PENDING orders can be confirmed");
        }
    }

    @Override
    public Order_SellerDTO getOrderById(Long orderId) {
        Order order = order_SellerRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return convertToDTO(order);
    }

}

