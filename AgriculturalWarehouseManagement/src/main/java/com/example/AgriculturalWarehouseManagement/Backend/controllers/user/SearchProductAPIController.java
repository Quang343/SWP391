package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.AddressBookResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ShopDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchProductAPIController {

    @Autowired
    private ShopDetailService shopDetailService;


    @GetMapping("/searchProduct/{keyword}")
    public ResponseEntity<List<ShopDetailResponse>> getAddressBook(@PathVariable String keyword) {
        // Filter get data
        List<ShopDetailResponse> shopDetailResponses = shopDetailService.getProductsSearchAPIs(keyword);

        return ResponseEntity.ok().body(shopDetailResponses);
    }
}
