package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

    private final UserService userService;

    @GetMapping("/loginAdmin")
    public String getLoginPage(){
        return "BackEnd/Admin/login";
    }

//    @GetMapping("/register")
//    public String getRegisterPage(){
//        return "BackEnd/signup";
//    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){
        return "FrontEnd/Deny";
    }

//    @GetMapping("/admin/profile")
//    public String getAdminProfilePage(Model model, Authentication authentication){
//        String email = authentication.getName();
//        User user = userService.findByEmail(email);
//
//        model.addAttribute("user", user);
//        model.addAttribute("userDTO", new UserDTO());
//        return "BackEnd/Admin/Profile";
//    }

    @GetMapping("/admin/profile")
    public String getAdminProfilePage(Model model, HttpSession session){

        UserResponse userResponse = (UserResponse)session.getAttribute("account");
        User user = userService.findById(userResponse.getUserID() * 1L);

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
