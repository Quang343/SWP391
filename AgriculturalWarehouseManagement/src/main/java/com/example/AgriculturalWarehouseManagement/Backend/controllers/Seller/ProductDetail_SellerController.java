package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetailEditResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetailResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.ProductDetail_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.CategoryWeight;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetailStatus;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.CategoryWeight_SellerRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.ProductDetail_SellerRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.IProductDetail_SellerService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seller/product-details")
@RequiredArgsConstructor
public class ProductDetail_SellerController {

    private final IProductDetail_SellerService productDetailService;
    private final ProductService productService;
    private final CategoryWeight_SellerRepository categoryWeightRepository;
    private final ProductDetail_SellerRepository productDetail_SellerRepository;

    // ✅ Thêm mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDetail_SellerDTO dto) {
        try {
            ProductDetail created = productDetailService.createProductDetail(dto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("message", ex.getMessage())
            );
        }
    }


    // ✅ Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDetail_SellerDTO dto) {
        try {
            ProductDetail updated = productDetailService.updateProductDetail(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("message", ex.getMessage())
            );
        }
    }

    // ✅ Xóa mềm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = productDetailService.deleteProductDetail(id);
        return deleted ? ResponseEntity.ok("Đã ẩn chi tiết sản phẩm") : ResponseEntity.notFound().build();
    }

    // ✅ Lấy danh sách detail theo product
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getByProduct(@PathVariable Long productId) {
        List<ProductDetail> list = productDetailService.getByProductId(productId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductDetail detail = productDetailService.findById(id);

        ProductDetailEditResponseDTO dto = ProductDetailEditResponseDTO.builder()
                .id(detail.getProductDetailId())
                .productName(detail.getProductID().getName())
                .weight(detail.getCategoryWeightID().getWeight()) // ✅ đóng ngoặc ()
                .unit(detail.getCategoryWeightID().getUnit())     // ✅ đóng ngoặc ()
                .price(detail.getPrice())
                .expiry(detail.getExpiry())
                .status(detail.getStatus().name())
                .build();

        return ResponseEntity.ok(dto);
    }



    // ✅ Lấy danh sách status để load dropdown
    @GetMapping("/statuses")
    public ResponseEntity<?> getStatuses() {
        List<String> statuses = Arrays.stream(ProductDetailStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category-weights/{categoryId}")
    public ResponseEntity<?> getCategoryWeights(@PathVariable Long categoryId) {
        List<CategoryWeight> list = categoryWeightRepository.findByCategory_Id(categoryId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/product-dto/{productId}")
    public ResponseEntity<?> getByProductIdWithDTO(@PathVariable Long productId) {
        List<ProductDetailResponseDTO> result = productDetailService.getProductDetailsByProductIdAsDTO(productId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-dto")
    public ResponseEntity<?> getAllProductDetailsAsDTO() {
        List<ProductDetail> details = productDetail_SellerRepository.findAll();
        List<ProductDetailResponseDTO> result = details.stream().map(detail -> ProductDetailResponseDTO.builder()
                .id(detail.getProductDetailId())
                .productName(detail.getProductID().getName())
                .detailName(detail.getDetailName())
                .price(detail.getPrice())
                .expiry(detail.getExpiry())
                .status(detail.getStatus().name())
                .build()
        ).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

}