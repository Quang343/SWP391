package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CartUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartUserController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private CartUserService cartUserService;

    @GetMapping("/cart")
    public String cart(Model model) {

        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        Object account = session.getAttribute("account");
        if (account == null) {
            session.invalidate();
            return "redirect:/login";
        }

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            int accountId = userEntity.getUserId();
            int productDetailIdCart = session.getAttribute("productDetailIdCart") == null ? -1 : Integer.parseInt(session.getAttribute("productDetailIdCart").toString());
            int quantityCart = session.getAttribute("quantityCart") == null ? -1 : Integer.parseInt(session.getAttribute("quantityCart").toString());

            // View cart
            if (productDetailIdCart == -1 || quantityCart == -1) {
                List<CartUserResponse> cartUserReponses = cartUserService.getCartByUserIds(accountId);
                double totalCart = 0;
                for (CartUserResponse cartUserReponse : cartUserReponses) {
                     totalCart += cartUserReponse.getTotalPrice();
                }
                model.addAttribute("totalCart", totalCart);
                model.addAttribute("cartUserReponses", cartUserReponses);
                return "FrontEnd/Home/cart";
            }

            // Add to cart in product detail
            ResponseResult<CartUserResponse> result =  cartUserService.insertCart(accountId,productDetailIdCart,quantityCart);
            if (result.isActive()){
                List<CartUserResponse> cartUserReponses = cartUserService.getCartByUserIds(accountId);
                double totalCart = 0;
                for (CartUserResponse cartUserReponse : cartUserReponses) {
                    totalCart += cartUserReponse.getTotalPrice();
                }
                model.addAttribute("totalCart", totalCart);
                model.addAttribute("cartUserReponses", cartUserReponses);
                session.removeAttribute("productDetailIdCart");
                session.removeAttribute("quantityCart");

                return "FrontEnd/Home/cart";
            }

        }

        return "FrontEnd/Home/cart";
    }
}
