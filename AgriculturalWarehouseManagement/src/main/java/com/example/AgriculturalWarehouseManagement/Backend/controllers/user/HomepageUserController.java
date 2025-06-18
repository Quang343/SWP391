package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductUsersHomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageUserController {

    @Autowired
    private ProductUsersHomepageService productUsersService;

    @RequestMapping("/")
    public String homePage() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {

        // HomePage Product user can view
        List<ProductUserHomepageResponse> productView = productUsersService.getProductUsersHomepages();
        List<List<ProductUserHomepageResponse>> groupedProducts = new ArrayList<>();
        for (int i = 0; i < productView.size(); i += 2) {
            int end = Math.min(i + 2, productView.size());
            groupedProducts.add(productView.subList(i, end));
        }
        model.addAttribute("groupedProducts", groupedProducts);

        // Top 9 of new product
        List<ProductUserHomepageResponse> productTop9 = productUsersService.getTop9NewOfProducts();
        List<List<ProductUserHomepageResponse>> groupedProductsTop9 = new ArrayList<>();
        for (int i = 0; i < productTop9.size(); i += 3) {
            int end = Math.min(i + 3, productTop9.size());
            groupedProductsTop9.add(productTop9.subList(i, end));
        }
        model.addAttribute("groupedProductsTop9", groupedProductsTop9);

        // Top 9 of price Product
        List<ProductUserHomepageResponse> productPriceTop9 = productUsersService.getTop9PriceOfProducts();
        List<List<ProductUserHomepageResponse>> groupedProductsPriceTop9 = new ArrayList<>();
        for (int i = 0; i < productPriceTop9.size(); i += 3) {
            int end = Math.min(i + 3, productPriceTop9.size());
            groupedProductsPriceTop9.add(productPriceTop9.subList(i, end));
        }
        model.addAttribute("groupedProductsPriceTop9", groupedProductsPriceTop9);

        // Top 9 of rating Product
        List<ProductUserHomepageResponse> productRatingTop9 = productUsersService.getTop9RatingOfProducts();
        List<List<ProductUserHomepageResponse>> groupedProductsRatingTop9 = new ArrayList<>();
        for (int i = 0; i < productRatingTop9.size(); i += 3) {
            int end = Math.min(i + 3, productRatingTop9.size());
            groupedProductsRatingTop9.add(productRatingTop9.subList(i, end));
        }
        model.addAttribute("groupedProductsRatingTop9", groupedProductsRatingTop9);

        return "FrontEnd/Home/home";
    }


}
