package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface User_SellerService {
    User_SellerDTO getUserByUsername(String username);
    User_SellerDTO updateUserProfile(User_SellerDTO dto);
    Optional<User_SellerDTO> getUserById(Long userId);
    void changePassword(Long userId, String currentPassword, String newPassword);
    String saveAvatar(Long userId, MultipartFile file) throws Exception;

}

