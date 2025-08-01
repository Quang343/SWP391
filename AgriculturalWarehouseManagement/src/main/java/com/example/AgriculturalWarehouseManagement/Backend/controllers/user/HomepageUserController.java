package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryUsersResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CartUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CategoryUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductUsersHomepageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomepageUserController {

    @Autowired
    private ProductUsersHomepageService productUsersService;

    @Autowired
    private CategoryUsersService categoryUsersService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private CartUserService cartUserService;


    @GetMapping("/")
    public String homePage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        // Homepage All category
        List<CategoryUsersResponse> categoryUsersResponses = categoryUsersService.getAllListCategory();
        for (CategoryUsersResponse categoryUsersResponse : categoryUsersResponses) {
            System.out.printf("Category Name: %s\n", categoryUsersResponse.toString());
        }
        model.addAttribute("categoryUsersResponses", categoryUsersResponses);

        // ProductMenu
        List<ProductUserHomepageResponse> productMenu = productUsersService.getProductUsersHomepages() == null ? new ArrayList<>() : productUsersService.getProductUsersHomepages();

        List<CategoryUsersResponse> firstThreeCategories = categoryUsersResponses == null
                ? new ArrayList<>()
                : categoryUsersResponses.stream()
                .sorted(Comparator.comparingInt(CategoryUsersResponse::getCategoryId)) // sắp xếp tăng dần theo categoryId
                .limit(3)
                .collect(Collectors.toList());


        List<ProductUserHomepageResponse> productMenu1 = new ArrayList<>();
        List<ProductUserHomepageResponse> productMenu2 = new ArrayList<>();
        List<ProductUserHomepageResponse> productMenu3 = new ArrayList<>();

        for (int i = 0; i < firstThreeCategories.size(); i++) {
            CategoryUsersResponse category = firstThreeCategories.get(i);
            Integer categoryId = category.getCategoryId();

            List<ProductUserHomepageResponse> productsInCategory = productMenu.stream()
                    .filter(p -> p.getCategoryId() == (categoryId)) // Dùng .equals()
                    .limit(6)
                    .collect(Collectors.toList());

            if (i == 0) {
                session.setAttribute("categoryName1", category.getCategoryName());
                productMenu1.addAll(productsInCategory);
            } else if (i == 1) {
                session.setAttribute("categoryName2", category.getCategoryName());
                productMenu2.addAll(productsInCategory);
            } else if (i == 2) {
                session.setAttribute("categoryName3", category.getCategoryName());
                productMenu3.addAll(productsInCategory);
            }
        }

        session.setAttribute("productMenu1", productMenu1);
        session.setAttribute("productMenu2", productMenu2);
        session.setAttribute("productMenu3", productMenu3);


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

        // View list Cart
        Object accountIdObj = session.getAttribute("accountId");
        if (accountIdObj != null) {
            int accountId = (int) accountIdObj;

            List<CartUserResponse> cartUserResponses = cartUserService.getCartByUserIds(accountId);

            int limit = Math.min(cartUserResponses.size(), 3);

            List<CartUserResponse> limitedCartUserResponses = cartUserResponses.subList(0, limit);

            model.addAttribute("sizeCart", cartUserResponses.size());
            model.addAttribute("cartUserResponses", limitedCartUserResponses);
            model.addAttribute("sizeCartBelow", Math.max(cartUserResponses.size() - 3, 0));


            double totalCart = 0;
            for (CartUserResponse cartUserResponse : cartUserResponses) {
                totalCart += cartUserResponse.getTotalPrice();
            }

            model.addAttribute("totalCart", totalCart);
        }


        return "FrontEnd/Home/home";
    }

    @PostMapping("/home")
    public String home(HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("accountAdmin");
        if (user != null) {
            if(user.getRole().getRoleName().equalsIgnoreCase("ADMIN")){
                return "redirect:/admin/orders";
            }
        }
        return "redirect:/home";
    }


}
