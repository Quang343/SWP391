package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryShopDetailsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryUsersResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CategoryUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductUsersHomepageService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ShopDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopDetailController {

    @Autowired
    private ShopDetailService  shopDetailService;

    @Autowired
    private CategoryUsersService categoryUsersService;

    @GetMapping("/shopDetail")
    public String shopDetail(Model model) {

        // Shop detail product user can view and Homepage All category
        List<ShopDetailResponse> shopDetailProducts = shopDetailService.getShopDetailProducts();
        model.addAttribute("shopDetailProducts", shopDetailProducts);

        List<CategoryUsersResponse> categoryUsersResponses = categoryUsersService.getAllListCategory();
        model.addAttribute("categoryUsersResponses", categoryUsersResponses);

        // Shop detail all category and count product
        List<CategoryShopDetailsResponse> categoryShopDetailsResponses = categoryUsersService.getAllCategoriesAndCountProducts();
        model.addAttribute("categoryShopDetailsResponse", categoryShopDetailsResponses);


        return "FrontEnd/Home/shop";
    }
}
