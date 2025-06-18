package com.example.AgriculturalWarehouseManagement.Backend.Seller;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.services.CategoryService;
import com.example.AgriculturalWarehouseManagement.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Product_SellerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


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
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) throws Exception {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(404).body("Product not found");
        }

        // Cập nhật có điều kiện, chỉ cập nhật nếu field khác null
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getCategoryId() != null) {
            Category category = null;
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
                return ResponseEntity.badRequest().body("Invalid status");
            }
        }

        productService.save(product); // lưu lại
        return ResponseEntity.ok("Product updated successfully");
    }

}