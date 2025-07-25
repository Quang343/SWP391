package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.SellerApplicationRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.SellerApplicationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/seller/applications")
@RequiredArgsConstructor
public class SellerApplicationController {

    private final SellerApplicationService sellerApplicationService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> submitApplication(
            @RequestParam("contractMonths") int contractMonths,
            @RequestParam("cvFile") MultipartFile cvFile,
            HttpSession session) {

        Integer idInt = (Integer) session.getAttribute("accountId");
        if (idInt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        Long userId = idInt.longValue();

        try {
            SellerApplication created = sellerApplicationService.createApplication(userId, contractMonths, cvFile);
            return ResponseEntity.ok(Map.of(
                    "message", "Gửi đơn thành công",
                    "applicationId", created.getId()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Lỗi hệ thống: " + e.getMessage()));
        }
    }


}
