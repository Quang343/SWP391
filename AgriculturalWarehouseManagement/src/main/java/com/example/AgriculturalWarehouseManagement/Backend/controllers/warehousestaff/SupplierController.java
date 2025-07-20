package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.SupplierDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
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

    private static final String UPLOAD_DIR = "D:/Uploads/Warehouse/";


    // Lấy tất cả nhà cung cấp với phân trang (API mới)
    @GetMapping("/paginated")
    @ResponseBody
    public Page<Suppliers> getPaginatedSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Suppliers> supplierPage = supplierService.getPaginatedSuppliers(page, size); // Use service method directly
        return supplierPage;
    }

    // Hiển thị trang danh sách nhà cung cấp với phân trang (Thymeleaf)
    @GetMapping("/admin/suppliers")
    public String listSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Suppliers> supplierPage = supplierService.getPaginatedSuppliers(page, size);
        model.addAttribute("suppliers", supplierPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", supplierPage.getTotalPages());
        model.addAttribute("totalItems", supplierPage.getTotalElements());
        return "BackEnd/WareHouse/supplier";
    }

    private String validateContactInfo(String contactInfo) {
        // Kiểm tra số điện thoại: 10 chữ số, bắt đầu bằng 0
        if (contactInfo.matches("^0\\d{9}$")) {
            return null; // Hợp lệ
        }

        // Kiểm tra email: chứa 1 @, không chứa ký tự đặc biệt ngoài @, ., _, -
        if (contactInfo.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            if (contactInfo.indexOf('@') != contactInfo.lastIndexOf('@')) {
                return "Email chỉ được chứa một ký tự @.";
            }
            return null; // Hợp lệ
        }

        return "Thông tin liên hệ phải là số điện thoại (10 chữ số, bắt đầu bằng 0) hoặc email hợp lệ.";
    }


    // Get Suppliers by ID
    @GetMapping("/{supplierID}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Integer supplierID) {
        SupplierDTO dto = supplierService.getSupplierById(supplierID);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto); // JSON
    }

    @PostMapping(value = "/createSupplier", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createSupplier(
            @RequestPart("supplierName") String supplierName,
            @RequestPart("contactInfo") String contactInfo,
            @RequestPart(value = "logo", required = false) MultipartFile logo) {

        // Validate input
        if (supplierName == null || supplierName.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            LOGGER.warning("Invalid input: supplierName or contactInfo is empty");
            return ResponseEntity.badRequest().body(Map.of("error", "Tên nhà cung cấp và thông tin liên hệ là bắt buộc."));
        }

        // Kiểm tra trùng tên
        if (supplierService.isSupplierNameExists(supplierName.trim(), null)) {
            LOGGER.warning("Supplier name '{}' already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Tên nhà cung cấp đã tồn tại."));
        }
        String normalizedName = supplierName.trim().replaceAll("\\s+", " ");
        if (!normalizedName.matches("^[a-zA-Z\\s\u00C0-\u00FF\u1EA0-\u1EFF]+$")) {
            LOGGER.warning("Invalid supplierName format: " + normalizedName);
            return ResponseEntity.badRequest().body(Map.of("error", "Tên nhà cung cấp chỉ chứa chữ cái (tiếng Việt),  khoảng trắng"));
        }

        // Kiểm tra trùng contactInfo (case-insensitive cho email)
        String normalizedContact = contactInfo.trim();
        if (normalizedContact.contains("@")) {
            normalizedContact = normalizedContact.toLowerCase();
        }

        if (supplierService.isContactInfoExists(normalizedContact, null)) {
            LOGGER.warning("Contact info '{}' already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Số điện thoại hoặc email đã được sử dụng."));
        }

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierName(supplierName);
        supplierDTO.setContactInfo(contactInfo);

        if (logo != null && !logo.isEmpty()) {
            try {
                // Validate file type
                if (!logo.getContentType().startsWith("image/")) {
                    LOGGER.warning("Invalid file type: " + logo.getContentType());
                    return ResponseEntity.badRequest().body(Map.of("error", "Chỉ chấp nhận file hình ảnh."));
                }

                String fileName = saveLogoFile(logo);
                supplierDTO.setLogo(fileName);
                LOGGER.info("Logo file set for createSupplier: " + fileName);
            } catch (Exception e) {
                LOGGER.severe("Failed to save logo file for createSupplier: " + e.getMessage());
                return ResponseEntity.badRequest().body(Map.of("error", "Không thể lưu logo: " + e.getMessage()));
            }
        } else {
            LOGGER.info("No logo file provided for createSupplier");
        }

        SupplierDTO createdSupplier = supplierService.createSupplier(supplierDTO);
        LOGGER.info("Created supplier with logo: " + createdSupplier.getLogo());
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{supplierID}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateSupplier(
            @PathVariable Integer supplierID,
            @RequestPart("supplierName") String supplierName,
            @RequestPart("contactInfo") String contactInfo,
            @RequestPart(value = "logo", required = false) MultipartFile logo) {

        // Validate input
        if (supplierName == null || supplierName.trim().isEmpty() || contactInfo == null || contactInfo.trim().isEmpty()) {
            LOGGER.warning("Invalid input: supplierName or contactInfo is empty");
            return ResponseEntity.badRequest().body(Map.of("error", "Tên nhà cung cấp và thông tin liên hệ là bắt buộc."));
        }

        // Kiểm tra trùng tên
        String normalizedName = supplierName.trim().replaceAll("\\s+", " ");
        if (!normalizedName.matches("^[a-zA-Z\\s\u00C0-\u00FF\u1EA0-\u1EFF]+$")) {
            LOGGER.warning("Invalid supplierName format: " + normalizedName);
            return ResponseEntity.badRequest().body(Map.of("error", "Tên nhà cung cấp chỉ chứa chữ cái (tiếng Việt),  khoảng trắng"));
        }

        if (supplierService.isSupplierNameExists(supplierName.trim(), supplierID)) {
            LOGGER.warning("Supplier name '{}' already exists for supplier ID: {}");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Tên nhà cung cấp đã tồn tại."));
        }

        // Validate contactInfo
        if (contactInfo == null || contactInfo.trim().isEmpty()) {
            LOGGER.warning("Invalid input: contactInfo is empty");
            return ResponseEntity.badRequest().body(Map.of("error", "Thông tin liên hệ là bắt buộc."));
        }
        String contactValidationError = validateContactInfo(contactInfo.trim());
        if (contactValidationError != null) {
            LOGGER.warning("Invalid contactInfo format: " + contactValidationError);
            return ResponseEntity.badRequest().body(Map.of("error", contactValidationError));
        }

        // Kiểm tra trùng contactInfo (case-insensitive cho email)
        String normalizedContact = contactInfo.trim();
        if (normalizedContact.contains("@")) {
            normalizedContact = normalizedContact.toLowerCase();
        }
        if (supplierService.isContactInfoExists(normalizedContact, supplierID)) {
            LOGGER.warning("Contact info '{}' already exists for supplier ID: {}");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Số điện thoại hoặc email đã được sử dụng."));
        }

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierID(supplierID);
        supplierDTO.setSupplierName(supplierName);
        supplierDTO.setContactInfo(contactInfo);

        if (logo != null && !logo.isEmpty()) {
            try {
                if (!logo.getContentType().startsWith("image/")) {
                    LOGGER.warning("Invalid file type: " + logo.getContentType());
                    return ResponseEntity.badRequest().body(Map.of("error", "Chỉ chấp nhận file hình ảnh."));
                }

                String fileName = saveLogoFile(logo);
                supplierDTO.setLogo(fileName);
                LOGGER.info("Logo file set for updateSupplier: " + fileName);
            } catch (Exception e) {
                LOGGER.severe("Failed to save logo file for updateSupplier: " + e.getMessage());
                return ResponseEntity.badRequest().body(Map.of("error", "Không thể lưu logo: " + e.getMessage()));
            }
        } else {
            LOGGER.info("No logo file provided for supplier ID: {}, keeping existing logo");
            // Không set logo để giữ giá trị hiện tại
        }

        SupplierDTO updatedSupplier = supplierService.updateSupplier(supplierID, supplierDTO);
        if (updatedSupplier == null) {
            LOGGER.warning("Supplier not found for ID: {}");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Không tìm thấy nhà cung cấp với ID: " + supplierID));
        }
        LOGGER.info("Updated supplier with logo: " + updatedSupplier.getLogo());
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


}