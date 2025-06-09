package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Get All Suppliers
    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return supplierService.getAllSuppliers(); // Trả về danh sách tất cả các Suppliers
    }

    // Đường dẫn thư mục lưu trữ ảnh
    private final String uploadPath = "/BackEnd/assets/imgproject/";

    // Get Suppliers by ID

    @GetMapping("/{supplierID}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Integer supplierID) {
        SupplierDTO dto = supplierService.getSupplierById(supplierID);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto); // JSON
    }

    // Create Suppliers
    @PostMapping("/createSupplier")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO createdSupplier = supplierService.createSupplier(supplierDTO);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{supplierID}", consumes = {"multipart/form-data"})
    public ResponseEntity<SupplierDTO> updateSupplier(
            @PathVariable Integer supplierID,
            @RequestPart("supplierName") String supplierName,
            @RequestPart("contactInfo") String contactInfo,
            @RequestPart(value = "logo", required = false) MultipartFile logo) {

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierName(supplierName);
        supplierDTO.setContactInfo(contactInfo);
        if (logo != null && !logo.isEmpty()) {
            // Xử lý tải lên tệp, ví dụ: lưu vào đĩa hoặc đám mây và đặt tên tệp trong DTO
            String logoFileName = saveLogoFile(logo); // Triển khai phương thức này
            supplierDTO.setLogo(logoFileName);
        }

        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierID, supplierDTO);
        return updatedSupplier != null ? ResponseEntity.ok(updatedSupplier) : ResponseEntity.notFound().build();
    }

    // Phương thức ví dụ để lưu tệp logo
    private String saveLogoFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String uploadDir = "/BackEnd/assets/imgproject/";
            java.nio.file.Files.copy(
                    file.getInputStream(),
                    java.nio.file.Paths.get(uploadDir + fileName),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Không thể lưu tệp logo", e);
        }
    }

    // Delete Suppliers
    @DeleteMapping("/{supplierID}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer supplierID) {
        supplierService.deleteSupplier(supplierID);
        return ResponseEntity.noContent().build();
    }

    // API xử lý upload logo
    @PostMapping("/uploadLogo")
    public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file) {
        // Kiểm tra nếu file có tồn tại
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        try {
            // Đảm bảo thư mục lưu trữ tồn tại
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();  // Tạo thư mục nếu chưa tồn tại
            }

            // Lưu file vào thư mục
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadPath + "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }


}