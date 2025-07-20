package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface User_SellerService {
    User_SellerDTO getUserByUsername(String username);
    User_SellerDTO updateUserProfile(User_SellerDTO dto);
    Optional<User_SellerDTO> getUserById(int userId);
    void changePassword(int userId, String currentPassword, String newPassword);
    String saveAvatar(int userId, MultipartFile file) throws Exception;

}

