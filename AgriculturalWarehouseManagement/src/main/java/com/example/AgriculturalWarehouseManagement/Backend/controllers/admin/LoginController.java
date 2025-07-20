package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.CustomUserDetailsService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "BackEnd/Admin/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "BackEnd/signup";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){
        return "FrontEnd/Deny";
    }

    @GetMapping("/admin/profile")
    public String getAdminProfilePage(Model model, Authentication authentication){
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("userDTO", new UserDTO());
        return "BackEnd/Admin/Profile";
    }

    @GetMapping("/clear-error")
    @ResponseBody
    public void clearError(HttpSession session) {
        session.removeAttribute("error");
    }
}
