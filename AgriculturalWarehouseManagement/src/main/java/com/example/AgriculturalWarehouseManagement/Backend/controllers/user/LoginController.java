package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.response.user.LoginResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.LoginRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // Thêm BCryptPasswordEncoder

    //
    @GetMapping("/login")
    public String login() {
        return "FrontEnd/Home/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("loginRequest") LoginRequest loginRequest,
                              @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe,
                              Model model,
                              HttpServletResponse response,
                              HttpServletRequest request) {
        System.out.println(rememberMe);
        User userEntity = userService.loadUserByEmail(loginRequest.getEmail());
        if (userEntity == null) {
            model.addAttribute("errorLogin", "Email not exists !!!");
            return "FrontEnd/Home/login";
        } else {
            String passwordDatabase = userEntity.getPasswordHash();
            if (loginRequest.getEmail().equals(userEntity.getEmail()) && passwordEncoder.matches(loginRequest.getPassword(), passwordDatabase)) {
                if (rememberMe == true) {

                    String emailCookieSaved = "";
                    Cookie[] cookies = request.getCookies(); // get data cookie
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("COOKIE_EMAIL")) {
                                emailCookieSaved = cookie.getValue();
                            }
                        }
                    }

                    if (emailCookieSaved != null && !emailCookieSaved.equals("")) {
                        if (session.getAttribute("auth_token") != null) {
                            return "redirect:/home";
                        } else {
                            return "redirect:/login";
                        }
                    } else {
                        String token = jwtTokenFilter.generateToken(userEntity.getEmail());

                        session.setAttribute("auth_token", token);
                        session.setMaxInactiveInterval(60 * 60);

                        Cookie emailCookie = new Cookie("COOKIE_EMAIL", loginRequest.getEmail());
                        Cookie passwordCookie = new Cookie("COOKIE_PASSWORD", loginRequest.getPassword());
                        response.addCookie(emailCookie);
                        response.addCookie(passwordCookie);

                        return "redirect:/home";
                    }

                } else {
                    // Set token
                    String token = jwtTokenFilter.generateToken(userEntity.getEmail());

                    session.setAttribute("auth_token", token);
                    session.setMaxInactiveInterval(60 * 60);

                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("COOKIE_EMAIL")) {
                                cookie.setMaxAge(0); // Mark cookies for delete
                                response.addCookie(cookie); // send cookie to browser and delete cookie
                            }

                            if (cookie.getName().equals("COOKIE_PASSWORD")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }
                    return "redirect:/home";
                }

            } else {
                model.addAttribute("errorLogin", "Wrong password or email !!!");
                return "FrontEnd/Home/login";
            }
        }

    }

}
