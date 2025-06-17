package com.example.AgriculturalWarehouseManagement.Backend.Seller;

import com.example.AgriculturalWarehouseManagement.models.Category;
import com.example.AgriculturalWarehouseManagement.models.Product;
import com.example.AgriculturalWarehouseManagement.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.services.CategoryService;
import com.example.AgriculturalWarehouseManagement.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}