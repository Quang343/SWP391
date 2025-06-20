package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopDetailService {

    @Autowired
    private ProductRepository productRepository;

    public List<ShopDetailResponse> getShopDetailProduct() {
        List<Object[]> raw = productRepository.rawShopDetailsOfProducts();

        return raw.stream().map(row -> new ShopDetailResponse(
                ((Number) row[0]).intValue(),       // productId
                ((Number) row[1]).intValue(),       // categoryId
                (String) row[2],                   // imageUrlGallery
                (String) row[3],                   // productName
                ((Number) row[4]).intValue(),   // ratingProductDetail
                ((Number) row[5]).intValue(),   // ratingCount
                (String) row[6],                   // productDetailName
                ((Number) row[7]).doubleValue(),   // productPrice
                ((String) row[8]),            // productWeight (weight + unit)
                (String) row[9]                 // productDescription
        )).toList();
    }


    public List<ShopDetailResponse> getShopDetailProducts() {

        if (getShopDetailProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ShopDetailResponse> response = getShopDetailProduct();

            return response;
        }

    }
}
