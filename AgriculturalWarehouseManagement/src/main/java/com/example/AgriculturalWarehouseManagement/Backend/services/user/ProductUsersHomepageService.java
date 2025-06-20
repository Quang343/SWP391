package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUsersHomepageService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductUserHomepageResponse> getProductUsersHomepage() {
        List<Object[]> raw = productRepository.rawHomepageData();

        return raw.stream().map(row -> new ProductUserHomepageResponse(
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


    public List<ProductUserHomepageResponse> getProductUsersHomepages() {

        if (getProductUsersHomepage().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductUserHomepageResponse> response = getProductUsersHomepage();

            return response;
        }

    }

    public List<ProductUserHomepageResponse> getTop9NewOfProduct() {
        List<Object[]> raw = productRepository.top9NewOfProducts();

        return raw.stream().map(row -> new ProductUserHomepageResponse(
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

    public List<ProductUserHomepageResponse> getTop9NewOfProducts() {

        if (getTop9NewOfProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductUserHomepageResponse> response = getTop9NewOfProduct();
            return response;
        }

    }

    public List<ProductUserHomepageResponse> getTop9PriceOfProduct() {
        List<Object[]> raw = productRepository.top9PriceOfProducts();

        return raw.stream().map(row -> new ProductUserHomepageResponse(
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

    public List<ProductUserHomepageResponse> getTop9PriceOfProducts() {

        if (getTop9PriceOfProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductUserHomepageResponse> response = getTop9PriceOfProduct();
            return response;
        }

    }

    public List<ProductUserHomepageResponse> getTop9RatingOfProduct() {
        List<Object[]> raw = productRepository.top9RatingOfProducts();

        return raw.stream().map(row -> new ProductUserHomepageResponse(
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

    public List<ProductUserHomepageResponse> getTop9RatingOfProducts() {

        if (getTop9RatingOfProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ProductUserHomepageResponse> response = getTop9RatingOfProduct();
            return response;
        }

    }

}
