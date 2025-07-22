package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WishlistResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CartUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
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
    private UserCustomerService userCustomerService;

    @Autowired
    private WishlistServices wishlistServices;

    @Autowired
    private CartUserService cartUserService;

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
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

        Object account = session.getAttribute("account");
        if (account == null) {
            session.invalidate();
            return "redirect:/login";
        }


        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            List<ProductUserHomepageResponse> wishlistResponses = wishlistServices.getListOfWishlist(userEntity.getUserId());

            for (ProductUserHomepageResponse wishlistResponse : wishlistResponses) {
                System.out.println("wishlistResponse: " + wishlistResponse.toString());
            }
            model.addAttribute("wishlistOfProductResponses", wishlistResponses);

            // View cart
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

            return "FrontEnd/Home/wishlist";
        }
    }

    @PostMapping("/deleteWishlistProduct")
    public String deleteWishlistProduct(@ModelAttribute("productId") int productId, Model model) {
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

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            ResponseResult<WishlistResponse> result = wishlistServices.deleteWishlistByProductIdAndUserId(userEntity.getUserId(), productId);

            return "redirect:/wishlist";
        }

    }

    @PostMapping("/deleteAllWishlistProduct")
    public String deleteAllWishlistProduct(Model model) {
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

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            ResponseResult<WishlistResponse> result = wishlistServices.deleteAllWishlist(userEntity.getUserId());

            return "redirect:/wishlist";
        }

    }


}
