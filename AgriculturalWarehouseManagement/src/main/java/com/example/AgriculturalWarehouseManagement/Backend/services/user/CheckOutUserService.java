package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CheckOutProductsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CartRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckOutUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    // View checkout products
    public List<CheckOutProductsResponse> getCheckOutProductDetailByUserID(int accountId) {
        List<Object[]> raw = cartRepository.rawGetCheckOutProductDetailByUserID(accountId);

        List<CheckOutProductsResponse> list = raw.stream().map(row -> new CheckOutProductsResponse(
                ((Number) row[0]).intValue(),               // userId
                ((Number) row[1]).intValue(),               // productId
                ((Number) row[2]).intValue(),               // productDetailId
                (String) row[3],                            // imageUrl
                (String) row[4],                            // productName
                (String) row[5],                            // sellerName
                (String) row[6],                            // productWeight
                ((Number) row[7]).doubleValue(),               // productPrice
                ((Number) row[8]).intValue(),               // totalQuantityProductDetail
                ((Number) row[9]).doubleValue()                // totalPriceProductDetail
        )).toList();
        return list;

    }

    public List<CheckOutProductsResponse> getCheckOutProductDetailByUserIDs(int userId) {
        if (getCheckOutProductDetailByUserID(userId).isEmpty()) {
            return new ArrayList<>();
        } else {
            return getCheckOutProductDetailByUserID(userId);
        }

    }
}
