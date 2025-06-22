package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.FilterShopDetailRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShopDetailController {

    @Autowired
    private ShopDetailService shopDetailService;

    @Autowired
    private CategoryUsersService categoryUsersService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

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

    @GetMapping("/filterProduct")
    public String filterProduct(
            @RequestParam(name = "category", required = false) List<Integer> categoryIds,
            @RequestParam(name = "rating", required = false) List<Integer> ratings,
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") Integer minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "1000000") Integer maxPrice,
            @RequestParam(name = "sortBy", required = false, defaultValue = "default") String sortBy,
            @RequestParam(name = "keyword", required = false) String searchName,
            Model model) {
        System.out.println(categoryIds);
        System.out.println(ratings);
        System.out.println(minPrice);
        System.out.println(maxPrice);
        System.out.println(sortBy);
        System.out.println(searchName);

        model.addAttribute("selectedCategories", categoryIds);
        model.addAttribute("selectedRating", ratings);
        session.setAttribute("minPrice", minPrice);
        session.setAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedSort", sortBy);
        if (searchName != null && !searchName.isEmpty()) {
            session.setAttribute("keyword", searchName);
        }

        if (searchName != null && session.getAttribute("keyword") != null) {
            session.setAttribute("keyword", searchName);
        }


        FilterShopDetailRequest filterShopDetailRequest = new FilterShopDetailRequest();
        filterShopDetailRequest.setCategoryIds(categoryIds);
        filterShopDetailRequest.setRatings(ratings);
        filterShopDetailRequest.setMinPrice(minPrice);
        filterShopDetailRequest.setMaxPrice(maxPrice);
        filterShopDetailRequest.setSortBy(sortBy);

        // Filter get data
        List<ShopDetailResponse> filterShopDetail = shopDetailService.getFilterShopDetailsOfProducts(filterShopDetailRequest, searchName);
        model.addAttribute("shopDetailProducts", filterShopDetail);

        List<CategoryUsersResponse> categoryUsersResponses = categoryUsersService.getAllListCategory();
        model.addAttribute("categoryUsersResponses", categoryUsersResponses);

        // Shop detail all category and count product
        List<CategoryShopDetailsResponse> categoryShopDetailsResponses = categoryUsersService.getAllCategoriesAndCountProducts();
        model.addAttribute("categoryShopDetailsResponse", categoryShopDetailsResponses);

        return "FrontEnd/Home/shop";
    }

    @PostMapping("/deleteFilterProduct")
    public String deleteFilterProduct() {
        session.removeAttribute("minPrice");
        session.removeAttribute("maxPrice");
        session.removeAttribute("keyword");

        return "redirect:/shopDetail";
    }


}
