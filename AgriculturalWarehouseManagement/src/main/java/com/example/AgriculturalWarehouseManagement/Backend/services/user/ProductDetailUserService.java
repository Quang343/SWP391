package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WeightCompareProductDetailsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailUserService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<WeightCompareProductDetailsResponse> getcompareProductDetail(Integer productId) {
        List<Object[]> raw = productDetailRepository.rawGetWeightCompareProductDetails(productId);

        return raw.stream().map(row -> new WeightCompareProductDetailsResponse(
                ((Number) row[0]).intValue(),       // productDetailId
                ((Number) row[1]).intValue(),       // productId
                ((Number) row[2]).intValue(),   // categoryWeightId
                ((Number) row[3]).intValue(),   // categoryId
                ((Number) row[4]).intValue(),   // expiry
                ((Number) row[5]).doubleValue(), //price
                (String) row[6]           // productWeight (weight + unit)
        )).toList();
    }


    public List<WeightCompareProductDetailsResponse> getcompareProductDetails(Integer productId) {
        if (getcompareProductDetail(productId).isEmpty()) {
            return new ArrayList<>();
        } else {
            List<WeightCompareProductDetailsResponse> response = getcompareProductDetail(productId);

            return response;
        }
    }


    public List<ProductDetailUserResponse> getProductDetailUser(Integer productDetaiId) {
        List<Object[]> raw = productDetailRepository.rawGetProductDetails(productDetaiId);

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),               // productId
                (String) row[1],                            // productName
                (String) row[2],                            // productDescription
                (String) row[3],                            // imageUrl
                ((Number) row[4]).intValue(),               // productDetailId
                (String) row[5],                            // productWeight
                ((Number) row[6]).intValue(),               // batchId
                ((Number) row[7]).intValue(),               // importedQuantity
                ((Number) row[8]).intValue(),               // soldQuantity
                ((java.sql.Date) row[9]).toLocalDate(),     // manufactureDate
                ((Number) row[10]).intValue(),              // expiry
                ((Number) row[11]).doubleValue(),              // Price
                ((java.sql.Date) row[12]).toLocalDate(),    // expiryDate
                ((Number) row[13]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[14]).intValue(),              // remainQuantity
                (String) row[15],                           // status
                (String) row[16],                            // expiryStatus
                ((Number) row[17]).intValue(),                  // avgRating
                ((Number) row[18]).intValue()                   // ratingCount
        )).toList();

    }

    public ProductDetailUserResponse getProductDetailUsers(Integer productDetaiId) {
        if (getProductDetailUser(productDetaiId).isEmpty()) {
            return new ProductDetailUserResponse();
        } else {
            List<ProductDetailUserResponse> response = getProductDetailUser(productDetaiId);
            return response.get(0);
        }
    }

}
