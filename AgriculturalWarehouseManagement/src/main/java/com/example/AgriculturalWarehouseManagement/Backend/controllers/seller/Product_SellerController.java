package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Product_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.CategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductImageService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Product_SellerController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    @PostMapping("/api/seller/products")
    public ResponseEntity<?> createProductForSeller(@Valid @RequestBody ProductDTO productDTO) {
        System.out.println("Received product creation request: " + productDTO);

        if (productService.existsByName(productDTO.getName())) {
            return ResponseEntity.badRequest().body("Tên sản phẩm đã tồn tại");
        }

        try {
            Product product = productService.createProduct(productDTO);

            String categoryName = "Unknown";
            try {
                Category category = categoryService.findById(productDTO.getCategoryId());
                categoryName = category.getName();
            } catch (Exception e) {
                System.out.println("Không tìm thấy category với ID: " + productDTO.getCategoryId());
            }

            Product_SellerDTO responseDTO = Product_SellerDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .status(product.getStatus().name())
                    .categoryName(categoryName)
                    .build();

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            e.printStackTrace(); // log lỗi cụ thể ra console
            return ResponseEntity.status(500).body("Đã xảy ra lỗi khi thêm sản phẩm.");
        }
    }

    @GetMapping("/api/seller/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/api/seller/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/api/seller/statuses")
    public ResponseEntity<?> getProductStatuses() {
        List<String> statuses = Arrays.stream(ProductStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(statuses);
    }

    @PutMapping("/api/seller/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(404).body("Không tìm thấy sản phẩm");
        }

        // Cập nhật có điều kiện, chỉ cập nhật nếu field khác null
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getCategoryId() != null) {
            Category category;
            try {
                category = categoryService.findById(dto.getCategoryId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            product.setCategory(category);
        }
        if (dto.getStatus() != null) {
            try {
                ProductStatus status = ProductStatus.valueOf(dto.getStatus());
                product.setStatus(status);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Trạng thái không hợp lệ");
            }
        }

        productService.save(product); // lưu lại
        return ResponseEntity.ok("Sản phẩm đã được cập nhật thành công");
    }

    @GetMapping("/api/seller/{id}/images")
    public ResponseEntity<?> getImagesByProduct(@PathVariable Long id) {
        List<Gallery> images = productImageService.findByProductId(id);

        // Trả về danh sách DTO đơn giản chỉ gồm id và imageUrl
        List<Map<String, Object>> result = images.stream().map(img -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", img.getGalleryId());
            map.put("imageUrl", img.getImageUrl());
            return map;
        }).toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/api/seller/product/{productId}/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable Long productId,
                                         @RequestParam("file") MultipartFile file) {
        System.out.println(">>>>> uploadImage được gọi với productId = " + productId);

        try {
            if (file == null || file.isEmpty()) return ResponseEntity.badRequest().body("File rỗng");
            if (!file.getContentType().startsWith("image/")) return ResponseEntity.badRequest().body("Không phải ảnh");

            String filename = storeFile(file); // giống hàm bạn đang dùng
            ProductImageDTO dto = ProductImageDTO.builder().imageUrl(filename).build();
            Gallery savedImage = productService.createProductImage(productId, dto);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedImage.getGalleryId());
            response.put("imageUrl", savedImage.getImageUrl());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi server: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/seller/product/{productId}/images/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long productId, @PathVariable int imageId) throws IOException {
        Optional<Gallery> image = Optional.ofNullable(productImageService.findById(imageId));
        if (image.isEmpty() || !image.get().getProduct().getId().equals(productId)) {
            return ResponseEntity.notFound().build();
        }

        // Xóa hẳn
        productImageService.deleteById(imageId);

        // (Optional) Nếu bạn muốn xóa cả file vật lý:
        Files.deleteIfExists(Paths.get(uploadDir + "/Seller", image.get().getImageUrl()));

        return ResponseEntity.ok("Ảnh đã được xóa vĩnh viễn.");
    }

    @Value("${app.upload.product-dir}")
    private String uploadDir;
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

        return uniqueFileName; // trả về tên file
    }

}