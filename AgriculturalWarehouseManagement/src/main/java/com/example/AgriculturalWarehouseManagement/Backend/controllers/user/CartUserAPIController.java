package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartTableUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.TotalCartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CartUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartUserAPIController {

    @Autowired
    private CartUserService cartUserService;

    @PutMapping("/quantityCartProductDetail/{cartId}/{productDetailId}/{quantity}")
    public ResponseEntity<ResponseResult<CartUserResponse>> updateAddressBook(@PathVariable int cartId,
                                                                              @PathVariable int productDetailId,
                                                                              @PathVariable int quantity) {

        ResponseResult<CartUserResponse> result = cartUserService.updateCartAPI(cartId, productDetailId, quantity);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/totalCart/{accountId}")
    public ResponseEntity<ResponseResult<TotalCartUserResponse>> getTotalCart(@PathVariable int accountId) {

        ResponseResult<TotalCartUserResponse> result = cartUserService.getTotalCart(accountId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/addToCart/{productId}/{userId}")
    public ResponseEntity<ResponseResult<CartTableUserResponse>> addToCart(@PathVariable int productId,
                                                                           @PathVariable int userId) {

        ResponseResult<CartTableUserResponse> result = cartUserService.addToCart(productId, userId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/addToCartProductDetail/{productDetailId}/{userId}")
    public ResponseEntity<ResponseResult<CartTableUserResponse>> addToCartProductDetail(@PathVariable int productDetailId,
                                                                                        @PathVariable int userId) {

        ResponseResult<CartTableUserResponse> result = cartUserService.addToCartProductDetail(productDetailId, userId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/listCart/{userId}")
    public ResponseEntity<ResponseResult<List<CartUserResponse>>> listCartUser(@PathVariable int userId) {

        ResponseResult<List<CartUserResponse>> result = cartUserService.getCartHomeByUserIds(userId);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<ResponseResult<CartTableUserResponse>> deleteCart(@PathVariable int cartId) {

        ResponseResult<CartTableUserResponse> result = cartUserService.deleteCartUser(cartId);

        return ResponseEntity.ok(result);
    }
}
