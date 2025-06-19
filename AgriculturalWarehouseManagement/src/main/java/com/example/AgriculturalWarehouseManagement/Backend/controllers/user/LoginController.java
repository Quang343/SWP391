package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.LoginRequest;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

            if (userEntity.getStatus().equals("Inactive")){
                model.addAttribute("errorLogin", "Account inactive !!!");
                return "FrontEnd/Home/login";
            }

            if (userEntity.getStatusGG().equals("Active")){
                model.addAttribute("errorLogin", "Email had already been used by Accout Google !!!");
                return "FrontEnd/Home/login";
            }

            String passwordDatabase = userEntity.getPasswordHash();

            if (rememberMe == true) { // True checkbox

                String emailCookieSaved = "";
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("COOKIE_EMAIL")) {
                            emailCookieSaved = cookie.getValue();
                        }
                    }
                }

                if (emailCookieSaved != null && !emailCookieSaved.equals("")) { // Check cookie not the first
                    if (session.getAttribute("auth_token") != null) {

                        // Giả mã token
                        Claims claims = jwtTokenFilter.decodeToken((String) session.getAttribute("auth_token"));
                        if (claims != null) {
                            return "redirect:/movePageRole";
                        } else {
                            return "redirect:/login";
                        }
                    } else {
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("COOKIE_EMAIL")) {
                                    cookie.setMaxAge(0); // Mark cookies for delete
                                    response.addCookie(cookie); // send cookie to browser and delete cookie
                                }

                                if (cookie.getName().equals("COOKIE_PASSWORD_FAKE")) {
                                    cookie.setMaxAge(0);
                                    response.addCookie(cookie);
                                }
                            }
                        }

                        return "redirect:/login";
                    }
                } else { // Check cookie the first

                    if (loginRequest.getEmail().equals(userEntity.getEmail()) && passwordEncoder.matches(loginRequest.getPassword(), passwordDatabase)) {
                        String token = jwtTokenFilter.generateToken(userEntity.getEmail());

                        session.setAttribute("auth_token", token);
                        session.setMaxInactiveInterval(60 * 60);

                        Cookie emailCookie = new Cookie("COOKIE_EMAIL", loginRequest.getEmail());
                        Cookie passwordFakeCookie = new Cookie("COOKIE_PASSWORD_FAKE", "HASHING");
                        response.addCookie(emailCookie);
                        response.addCookie(passwordFakeCookie);

                        return "redirect:/movePageRole";

                    } else {
                        model.addAttribute("errorLogin", "Wrong password or email !!!");
                        return "FrontEnd/Home/login";
                    }
                }

            } else {

                String emailCookieSaved = "";
                Cookie[] cookies = request.getCookies(); // Get data cookie
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("COOKIE_EMAIL")) {
                            emailCookieSaved = cookie.getValue();
                        }
                    }
                }

                if (emailCookieSaved != null && !emailCookieSaved.equals("")) {  // Check cookie not the first
                    if (session.getAttribute("auth_token") != null) { // check time toke
                        // Giả mã token
                        Claims claims = jwtTokenFilter.decodeToken((String) session.getAttribute("auth_token"));
                        if (claims != null) {

                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("COOKIE_EMAIL")) {
                                        cookie.setMaxAge(0); // Mark cookies for delete
                                        response.addCookie(cookie); // send cookie to browser and delete cookie
                                    }

                                    if (cookie.getName().equals("COOKIE_PASSWORD_FAKE")) {
                                        cookie.setMaxAge(0);
                                        response.addCookie(cookie);
                                    }
                                }
                            }

                            return "redirect:/movePageRole";
                        } else {
                            return "redirect:/login";
                        }
                    } else {
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("COOKIE_EMAIL")) {
                                    cookie.setMaxAge(0); // Mark cookies for delete
                                    response.addCookie(cookie); // send cookie to browser and delete cookie
                                }

                                if (cookie.getName().equals("COOKIE_PASSWORD_FAKE")) {
                                    cookie.setMaxAge(0);
                                    response.addCookie(cookie);
                                }
                            }
                        }

                        return "redirect:/login";
                    }
                } else {  // Check cookie the first

                    if (loginRequest.getEmail().equals(userEntity.getEmail()) && passwordEncoder.matches(loginRequest.getPassword(), passwordDatabase)) {
                        // Set token
                        String token = jwtTokenFilter.generateToken(userEntity.getEmail());

                        session.setAttribute("auth_token", token);
                        session.setMaxInactiveInterval(60 * 60);

                        return "redirect:/movePageRole";
                    } else {
                        model.addAttribute("errorLogin", "Wrong password or email !!!");
                        return "FrontEnd/Home/login";
                    }

                }

            }

        }

    }

}
