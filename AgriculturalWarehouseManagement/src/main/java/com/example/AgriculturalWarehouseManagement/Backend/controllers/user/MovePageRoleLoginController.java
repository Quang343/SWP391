package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovePageRoleLoginController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserService userService;

    @PostMapping("/movePageRole")
    public String MovePageRole() {

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
            if (userEntity.getRole().getRoleName().equals("admin")) {
                return "redirect:/login";
            } else if (userEntity.getRole().getRoleName().equals("user")) {

                UserResponse userResponse = userService.getUser(userEntity);
                session.setAttribute("account", userResponse);
                session.setAttribute("accountId", userResponse.getUserID());
                System.out.println(session.getAttribute("accountId").toString());
                return "redirect:/home";
            } else if (userEntity.getRole().getRoleName().equals("blogger")) {
                return "redirect:/login";
            } else if (userEntity.getRole().getRoleName().equals("seller")) {
                return "redirect:/login";
            } else if (userEntity.getRole().getRoleName().equals("warehourestaff")) {
                return "redirect:/login";
            }
            return "redirect:/login";
        }
    }

}
