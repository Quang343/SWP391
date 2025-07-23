package com.example.AgriculturalWarehouseManagement.Backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Component
public class StoreFile {
    @Value("${app.upload.product-dir}")
    public String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("Empty file name");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //Thêm UUID vào trước để đảm bảo fileName là duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileName;
        //Đường dẫn đến thư mục mà bạn muốn lưu file
//        java.nio.file.Path uploadDir = Paths.get("src/main/resources/static/Backend/assets/images/category");
        //Kiểm tra và tạo nếu thư mục chưa tồn tại
        Path uploadPath = Paths.get(uploadDir, "Admin");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        //Đường dẫn đầy đủ đến file
        Path destination = uploadPath.resolve(uniqueFileName);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
