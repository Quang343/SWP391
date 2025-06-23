package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.PageConstant;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.FilterShopDetailRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryShopDetailsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CategoryUsersResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CategoryUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ShopDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ShopDetailController {

    @Autowired
    private ShopDetailService shopDetailService;

    @Autowired
    private CategoryUsersService categoryUsersService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @GetMapping("/shopDetail")
    public String shopDetail(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                             @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                             Model model) {
        if (page.equals(0)) {
            if (categoryId != null && categoryId > 0) {

                page = 1; // Nếu page = 0

                List<Integer> categoryIds = new ArrayList<>();
                categoryIds.add(categoryId);
                model.addAttribute("selectedCategories", categoryIds);
                System.out.println(categoryIds);
                System.out.println(page);

                // Shop detail product user can view by categoryId
                List<ShopDetailResponse> shopDetailProducts = shopDetailService.getShopDetailsOfProductsByCategoryIds(categoryId);

                // TotalRecords
                int totalRecords = shopDetailProducts.size();
                // Total pages
                int totalPages = (totalRecords % PageConstant.size) == 0
                        ? (totalRecords / PageConstant.size)
                        : (totalRecords / PageConstant.size) + 1;
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("currentPage", page);

                List<ShopDetailResponse> shopDetailProductsPage = shopDetailService.getShopDetailsOfProductsByCategoryIdPages(categoryId, page);
                model.addAttribute("shopDetailProducts", shopDetailProductsPage);

                model.addAttribute("checkPageCategorys", categoryIds);
            } else {

                page = 1; // Nếu page = 0

                // Shop detail product user can view
                List<ShopDetailResponse> shopDetailProducts = shopDetailService.getShopDetailProducts();

                // TotalRecords
                int totalRecords = shopDetailProducts.size();

                // Total pages
                int totalPages = (totalRecords % PageConstant.size) == 0
                        ? (totalRecords / PageConstant.size)
                        : (totalRecords / PageConstant.size) + 1;

                model.addAttribute("totalPages", totalPages);
                model.addAttribute("currentPage", page);

                List<ShopDetailResponse> shopDetailProductsPage = shopDetailService.getShopDetailProductPages(page);
                model.addAttribute("shopDetailProducts", shopDetailProductsPage);

                model.addAttribute("checkShopDetailProducts", "true");
            }
        } else { // Nếu ở trong shopdetail nhấn phân trang
            if (categoryId != null && categoryId > 0) {
                List<Integer> categoryIds = new ArrayList<>();
                categoryIds.add(categoryId);
                model.addAttribute("selectedCategories", categoryIds);

                // Shop detail product user can view by categoryId
                List<ShopDetailResponse> shopDetailProducts = shopDetailService.getShopDetailsOfProductsByCategoryIds(categoryId);

                // TotalRecords
                int totalRecords = shopDetailProducts.size();
                // Total pages
                int totalPages = (totalRecords % PageConstant.size) == 0
                        ? (totalRecords / PageConstant.size)
                        : (totalRecords / PageConstant.size) + 1;
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("currentPage", page);

                List<ShopDetailResponse> shopDetailProductsPage = shopDetailService.getShopDetailsOfProductsByCategoryIdPages(categoryIds.get(0), page);
                model.addAttribute("shopDetailProducts", shopDetailProductsPage);

                model.addAttribute("checkPageCategorys", categoryIds);
            } else {

                // Shop detail product user can view
                List<ShopDetailResponse> shopDetailProducts = shopDetailService.getShopDetailProducts();

                // TotalRecords
                int totalRecords = shopDetailProducts.size();

                // Total pages
                int totalPages = (totalRecords % PageConstant.size) == 0
                        ? (totalRecords / PageConstant.size)
                        : (totalRecords / PageConstant.size) + 1;

                model.addAttribute("totalPages", totalPages);
                model.addAttribute("currentPage", page);

                List<ShopDetailResponse> shopDetailProductsPage = shopDetailService.getShopDetailProductPages(page);
                model.addAttribute("shopDetailProducts", shopDetailProductsPage);

                model.addAttribute("checkShopDetailProducts", "true");
            }

        }


        // Shop detail category by product
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
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            HttpServletRequest request,
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

        // TotalRecords
        int totalRecords = filterShopDetail.size();

        // Total pages
        int totalPages = (totalRecords % PageConstant.size) == 0
                ? (totalRecords / PageConstant.size)
                : (totalRecords / PageConstant.size) + 1;

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        // Get link url
        String contextPath = request.getContextPath(); // "/AgriculturalWarehouseManagement"
        String uri = request.getRequestURI();          // "/AgriculturalWarehouseManagement/filterProduct"
        String path = uri.replaceFirst(contextPath + "/", ""); // Xóa /AgriculturalWarehouseManagement/ -> để lấy "filterProduct"

        String queryString = request.getQueryString(); // e.g., category=1&minPrice=0&sortBy=default&page=2
        String filteredParams = Arrays.stream(Optional.ofNullable(queryString).orElse("")
                        .split("&"))
                .filter(p -> !p.startsWith("page=") && !p.isBlank()) // Lọc bỏ tham số bắt đầu bằng "page="
                .collect(Collectors.joining("&")); // "category=1&minPrice=0&sortBy=default"

        String baseUrl = path + (filteredParams.isEmpty() ? "" : "?" + filteredParams);
        model.addAttribute("baseUrl", baseUrl);

        // filterShopDetailPages
        List<ShopDetailResponse> filterShopDetailPages = shopDetailService.getFilterShopDetailsOfProductPages(filterShopDetailRequest, searchName, page);
        model.addAttribute("shopDetailProducts", filterShopDetailPages);
        model.addAttribute("checkFilterShopDetailProducts", "true");

        // Shop detail category by product
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
