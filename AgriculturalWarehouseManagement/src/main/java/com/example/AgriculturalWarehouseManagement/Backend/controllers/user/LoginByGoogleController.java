package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.GoogleLogin;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.GoogleAccountRequest;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class LoginByGoogleController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @GetMapping(value = "/loginGG",  params = "code")
    private String loginGG(@RequestParam String code, RedirectAttributes redirectAttributes) throws IOException {
        if (code == null || code.isEmpty()) {

            return "redirect:/login";
        }

        GoogleLogin googleLogin = new GoogleLogin();
        String accessToken = googleLogin.getToken(code);
        GoogleAccountRequest googleAccountRequest = googleLogin.getUserInfo(accessToken);

        User user = userService.loadUserByEmail(googleAccountRequest.getEmail());
        System.out.println(googleAccountRequest.toString());
        if (user == null) {
            ResponseResult<User> resultLoginGG = userService.inserUserGGService(googleAccountRequest);

            String token = jwtTokenFilter.generateToken(googleAccountRequest.getEmail());
            session.setAttribute("authToken", token);
            session.setMaxInactiveInterval(20*60);
            return "redirect:/movePageRole";
        } else {
            if (user.getStatus().equals("Inactive")) {
                redirectAttributes.addFlashAttribute("errorLoginGG", "Account inactive !!!");
                return "redirect:/login";
            }

            if (user.getStatusGG().equals("Inactive") && user.getStatus().equals("Active")) {
                redirectAttributes.addFlashAttribute("errorLoginGG", "Email had register by account !!!");
                return "redirect:/login";
            } else if (user.getStatusGG().equals("Active") && user.getStatus().equals("Active")) {
                String token = jwtTokenFilter.generateToken(googleAccountRequest.getEmail());
                session.setAttribute("authToken", token);
                session.setMaxInactiveInterval(20*60);

                return "redirect:/movePageRole";
            } else {
                return "redirect:/login";
            }


        }


    }

    @GetMapping(value = "/loginGG", params = "error")
    private String ggFailure(@RequestParam String error,
                             RedirectAttributes redirect) {
        return "redirect:/login";
    }

}
