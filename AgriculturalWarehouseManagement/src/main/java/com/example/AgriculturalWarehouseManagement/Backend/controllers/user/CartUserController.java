package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartUserController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String cart() {

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

            int accountId = userEntity.getUserID();
            int productDetailIdCart = session.getAttribute("productDetailIdCart") == null ? 0 : Integer.parseInt(session.getAttribute("productDetailIdCart").toString());
            int quantityCart = session.getAttribute("quantityCart") == null ? 0 : Integer.parseInt(session.getAttribute("quantityCart").toString());


        }


        return "FrontEnd/Home/cart";
    }
}
