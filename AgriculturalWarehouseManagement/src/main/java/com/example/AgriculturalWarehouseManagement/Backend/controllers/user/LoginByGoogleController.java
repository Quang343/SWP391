package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.GoogleLogin;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.GoogleAccountRequest;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class LoginByGoogleController {



    @GetMapping("loginGG")
    private String loginGG(@RequestParam String code) throws IOException {

        if (code == null || code.isEmpty()) {
            return "FrontEnd/Home/login";
        }
        GoogleLogin googleLogin = new GoogleLogin();
        String accessToken = googleLogin.getToken(code);

        GoogleAccountRequest googleAccountRequest = googleLogin.getUserInfo(accessToken);
        System.out.println("hello"+googleAccountRequest);
        System.out.println("Helo111");
        return "FrontEnd/Home/login";
    }

}
