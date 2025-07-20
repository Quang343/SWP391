package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WishlistAPIResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WishlistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistProductAPIController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private WishlistServices wishlistServices;


    @PostMapping("/wishlistProduct/{userid}/{productid}")
    public ResponseEntity<WishlistAPIResponse> addWishlistProduct(@PathVariable Long userid,@PathVariable Long productid) {

            System.out.println("hello"+productid);
            WishlistAPIResponse wishlistAPIResponse = wishlistServices.insertWishlist(Math.toIntExact(userid), Math.toIntExact(productid));

            return ResponseEntity.ok(wishlistAPIResponse);
    }

}
