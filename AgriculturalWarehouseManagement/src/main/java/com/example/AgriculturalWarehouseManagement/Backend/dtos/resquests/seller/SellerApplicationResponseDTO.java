package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerApplicationResponseDTO {
    private Long id;

    // Thông tin hiển thị: tên, email,... (lấy từ User)
    private String fullName;
    private String email;
    private String phone;

    private Integer contractMonths;
    private String cvImage;
    private SellerApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDate contractExpiryDate;
    private LocalDate cancelDeadline;

}

