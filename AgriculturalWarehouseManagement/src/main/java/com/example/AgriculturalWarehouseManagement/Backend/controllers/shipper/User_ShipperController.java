package com.example.AgriculturalWarehouseManagement.Backend.controllers.shipper;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ChangePasswordRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.User_SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

//@author: Đào Huy Hoàng

@RestController
@RequestMapping("/api/shipper/user")
@RequiredArgsConstructor
public class User_ShipperController {

    private final User_SellerService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 🔹 Lấy thông tin user theo username
    @GetMapping("/{username}")
    public ResponseEntity<User_SellerDTO> getUserProfile(@PathVariable String username) {
        User_SellerDTO dto = userService.getUserByUsername(username);
        return ResponseEntity.ok(dto);
    }

    // 🔹 Cập nhật thông tin user
    @PutMapping
    public ResponseEntity<User_SellerDTO> updateUserProfile(@RequestBody User_SellerDTO dto) {
        User_SellerDTO updated = userService.updateUserProfile(dto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Tạm thời: Lấy thông tin user theo userId (do chưa có session)
    @GetMapping("/by-id/{id}")
    public ResponseEntity<User_SellerDTO> getUserProfileById(@PathVariable Long id) {
        return userService.getUserById(id) // truyền tạm 40L sau truyền lại id
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {

            userService.changePassword(request.getUserId(), request.getCurrentPassword(), request.getNewPassword());
            return ResponseEntity.ok().body(Map.of("message", "Đổi mật khẩu thành công"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Lỗi server"));
        }
    }

    @PostMapping("/{userId}/avatar")
    public ResponseEntity<?> uploadAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            String avatarUrl = userService.saveAvatar(userId, file);
            return ResponseEntity.ok(Map.of("avatarUrl", avatarUrl));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Không thể upload ảnh."));
        }
    }


}


