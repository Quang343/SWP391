package com.example.AgriculturalWarehouseManagement.Backend.services.admin.product;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.ProductImageDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(int id, ProductDTO productDTO);
    boolean deleteProduct(int id);
    List<Product> findAll();
    Product findById(int id);
    boolean existsByName(String name);

    Gallery createProductImage(int productId, ProductImageDTO productImageDTO) throws Exception;

    Page<Product> findAll(Pageable pageable);
}
