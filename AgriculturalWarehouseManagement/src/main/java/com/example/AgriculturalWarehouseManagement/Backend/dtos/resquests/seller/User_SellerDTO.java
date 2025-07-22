package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User_SellerDTO {
    private Integer userId;
    private String fullName;
    private String gender;
    private String dob;
    private String createdAt;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String image;
}
