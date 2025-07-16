package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private int userId;
    private String currentPassword;
    private String newPassword;
}
