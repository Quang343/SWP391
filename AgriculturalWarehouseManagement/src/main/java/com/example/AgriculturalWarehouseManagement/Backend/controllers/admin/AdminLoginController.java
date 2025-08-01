package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.SellerApplicationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

    private final UserService userService;

    private final NotificationService notificationService;

    private final SellerApplicationService sellerApplicationService;

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

        UserResponse userResponse = (UserResponse)session.getAttribute("accountAdmin");
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
