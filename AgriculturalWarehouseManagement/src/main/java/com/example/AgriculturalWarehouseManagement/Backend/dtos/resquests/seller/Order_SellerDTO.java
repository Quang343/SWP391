package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order_SellerDTO {
    private Long orderId;
    private String orderCode;
    private LocalDateTime orderDate;
    private String phone;
    private String address;
    private BigDecimal totalAmount;
    private BigDecimal finalAmount;
    private String status;
}

