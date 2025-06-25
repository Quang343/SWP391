package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WeightCompareProductDetailsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductDetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductDetailUserAPIController {

    @Autowired
    private ProductDetailUserService productDetailUserServices;

    @GetMapping("/productDetailAPI/{productId}")
    public ResponseEntity< List<WeightCompareProductDetailsResponse>> getWeightToCompareProductDetails(@PathVariable Integer productId) {

        List<WeightCompareProductDetailsResponse> compareProductDetailsResponses = productDetailUserServices.getcompareProductDetails(productId);

        return ResponseEntity.ok().body(compareProductDetailsResponses);
    }

}
