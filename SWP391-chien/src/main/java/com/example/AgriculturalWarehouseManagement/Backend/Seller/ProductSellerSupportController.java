package com.example.AgriculturalWarehouseManagement.Backend.Seller;

import com.example.AgriculturalWarehouseManagement.dtos.ProductDTO;
import com.example.AgriculturalWarehouseManagement.dtos.Seller.ProductSellerResponseDTO;
import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.services.ICategoryService;
import com.example.AgriculturalWarehouseManagement.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/seller/products")
@RequiredArgsConstructor
public class ProductSellerSupportController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createProductForSeller(@Valid @RequestBody ProductDTO productDTO) {
        System.out.println("Received product creation request: " + productDTO);

        if (productService.existsByName(productDTO.getName())) {
            return ResponseEntity.badRequest().body("Product name already exists");
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

            ProductSellerResponseDTO responseDTO = ProductSellerResponseDTO.builder()
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

    @PutMapping("/{id}/hide")
    public ResponseEntity<?> hideProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id); // gọi method có sẵn
        if (isDeleted) {
            return ResponseEntity.ok("Product has been hidden (soft deleted)");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }

}

