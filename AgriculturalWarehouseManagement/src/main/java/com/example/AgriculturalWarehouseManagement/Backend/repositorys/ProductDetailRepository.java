package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Integer> {
    List<ProductDetail> findProductDetailsByProductID(Product productID);
}
