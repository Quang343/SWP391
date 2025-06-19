package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WishlistResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WishlistServices;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishListProductController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistServices wishlistServices;

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        String token = (String) session.getAttribute("auth_token");

        if (token == null) {
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            return "redirect:/login";
        } else {

            List<ProductUserHomepageResponse> wishlistResponses = wishlistServices.getListOfWishlist(userEntity.getUserID());

            for (ProductUserHomepageResponse wishlistResponse : wishlistResponses) {
                System.out.println("wishlistResponse: " + wishlistResponse.toString());
            }
            model.addAttribute("wishlistOfProductResponses", wishlistResponses);
            return "FrontEnd/Home/wishlist";
        }
    }

    @PostMapping("/deleteWishlistProduct")
    public String deleteWishlistProduct(@ModelAttribute("productId") int productId, Model model) {
        String token = (String) session.getAttribute("auth_token");

        if (token == null) {
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            return "redirect:/login";
        } else {

            ResponseResult<WishlistResponse> result = wishlistServices.deleteWishlistByProductIdAndUserId(userEntity.getUserID(), productId);

            return "redirect:/wishlist";
        }

    }

    @PostMapping("/deleteAllWishlistProduct")
    public String deleteAllWishlistProduct(Model model) {
        String token = (String) session.getAttribute("auth_token");

        if (token == null) {
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            return "redirect:/login";
        } else {

            ResponseResult<WishlistResponse> result = wishlistServices.deleteAllWishlist(userEntity.getUserID());

            return "redirect:/wishlist";
        }

    }


}
