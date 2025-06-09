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
import java.util.logging.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(SupplierController.class.getName());

    private static final String UPLOAD_DIR = "src/main/resources/static/BackEnd/assets/imgproject/";

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

        // Validate input
        if (supplierName == null || supplierName.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            LOGGER.warning("Invalid input: supplierName or contactInfo is empty");
            return ResponseEntity.badRequest().build();
        }

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierID(supplierID);
        supplierDTO.setSupplierName(supplierName);
        supplierDTO.setContactInfo(contactInfo);

        if (logo != null && !logo.isEmpty()) {
            try {
                // Validate file type
                if (!logo.getContentType().startsWith("image/")) {
                    LOGGER.warning("Invalid file type: " + logo.getContentType());
                    return ResponseEntity.badRequest().body(new SupplierDTO("Only image files are allowed."));
                }

                String fileName = saveLogoFile(logo);
                supplierDTO.setLogo(fileName);
            } catch (Exception e) {
                LOGGER.severe("Failed to save logo file for updateSupplier: " + e.getMessage());
                throw new RuntimeException("Failed to save logo file: " + e.getMessage(), e);
            }
        } else {
            LOGGER.info("No logo file provided for supplier ID: " + supplierID);
        }

        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierID, supplierDTO);
        if (updatedSupplier == null) {
            LOGGER.warning("Supplier not found for ID: " + supplierID);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSupplier);
    }

    private String saveLogoFile(MultipartFile file) {
        try {
            // Define upload directory
            Path uploadDir = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            LOGGER.info("Upload directory: " + uploadDir.toString());

            // Create directory if it doesn't exist
            Files.createDirectories(uploadDir);

            // Handle file naming to avoid overwrite
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                originalFileName = "default.jpg";
            }
            String fileName = originalFileName;
            Path filePath = uploadDir.resolve(fileName);
            int counter = 1;
            while (Files.exists(filePath)) {
                String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                fileName = baseName + "_" + counter + extension;
                filePath = uploadDir.resolve(fileName);
                counter++;
            }

            // Save the file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("File saved successfully: " + filePath.toString());

            // Verify file exists
            if (Files.exists(filePath)) {
                LOGGER.info("File verified: " + filePath.toString() + " exists");
            } else {
                LOGGER.severe("File not found after saving: " + filePath.toString());
                throw new RuntimeException("File was not saved to " + filePath.toString());
            }

            return fileName;
        } catch (Exception e) {
            LOGGER.severe("Failed to save logo file: " + e.getMessage());
            throw new RuntimeException("Không thể lưu tệp logo: " + e.getMessage(), e);
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