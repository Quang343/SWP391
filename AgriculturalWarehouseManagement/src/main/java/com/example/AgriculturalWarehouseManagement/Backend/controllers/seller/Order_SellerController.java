package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.Order_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/orders")
public class Order_SellerController {

    @Autowired
    private Order_SellerService order_SellerService;

    @GetMapping
    public List<Order_SellerDTO> getAllOrders_Seller() {
        return order_SellerService.getAllOrders_Seller();
    }
    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<String> confirmOrder(@PathVariable Long orderId) {
        order_SellerService.confirmOrder(orderId);
        return ResponseEntity.ok("Đã xác nhận đơn hàng thành công");
    }

    // ✅ GET đơn hàng theo ID
    @GetMapping("/{id}")
    public Order_SellerDTO getOrderById(@PathVariable("id") Long id) {
        return order_SellerService.getOrderById(id);
    }
}

