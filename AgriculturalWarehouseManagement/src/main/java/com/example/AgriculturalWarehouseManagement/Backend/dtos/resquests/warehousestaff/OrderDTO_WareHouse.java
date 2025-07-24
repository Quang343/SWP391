package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO_WareHouse {
    private Long orderId;
    private String address;
    private BigDecimal discountAmount;
    private String email;
    private BigDecimal finalAmount;
    private String fullName;
    private String note;
    private String orderCode;
    private LocalDateTime orderDate;
    private String phone;
    private String status;
    private BigDecimal totalAmount;
    private String voucherCode;
    private Integer userId;
    private Long voucherId;
}
