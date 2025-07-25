package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.SellerApplicationRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.SellerApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerApplicationService {

    private final SellerApplicationRepository sellerApplicationRepository;
    private final UserRepository userRepository;

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    public SellerApplication createApplication(Long userId, int contractMonths, MultipartFile cvFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        // Kiểm tra nếu đã tồn tại đơn PENDING
        if (sellerApplicationRepository.existsByUserAndStatus(user, SellerApplicationStatus.PENDING)) {
            throw new RuntimeException("Bạn đã có một đơn đăng ký đang chờ duyệt");
        }

        String filename;
        try {
            filename = storeFile(cvFile);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file CV: " + e.getMessage());
        }

        SellerApplication app = new SellerApplication();
        app.setUser(user);
        app.setContractMonths(contractMonths);
        app.setCvImage(filename);
        app.setCreatedAt(LocalDateTime.now());
        app.setStatus(SellerApplicationStatus.PENDING);

        return sellerApplicationRepository.save(app);
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("Tên tệp trống");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex);
        }

        String uniqueFileName = UUID.randomUUID().toString() + extension;

        Path uploadPath = Paths.get(uploadDir + "/Seller");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path destination = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }
}

