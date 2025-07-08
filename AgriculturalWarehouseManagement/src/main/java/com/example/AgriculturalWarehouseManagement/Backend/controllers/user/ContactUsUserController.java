package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.ContactUsUserRequests;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ContactUsUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.ContactUsUser;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.AddressBookService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ContactUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactUsUserController {

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactUserService contactUserService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @GetMapping("/contactususer")
    public String contactUsUser() {
        return "FrontEnd/Home/contactUs";
    }

    @PostMapping("/contactususer")
    public String contactUsUser(@ModelAttribute ContactUsUserRequests contactUsUserRequests, Model model) {

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
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {

            UserResponse userResponse = (UserResponse) session.getAttribute("account");
            System.out.println("HELLO NA" + contactUsUserRequests.toString());
            ResponseResult<ContactUsUserResponse> result = contactUserService.insertContactUsUser(contactUsUserRequests, userResponse.getUserID());

            if (result.isActive()) {
                session.setAttribute("successContactUser", result.getMessage());
                return "redirect:/contactUserSuccess";
            } else {
                model.addAttribute("errorContactUser", result.getMessage());
                return "FrontEnd/Home/contactUs";
            }

        }
    }

    @GetMapping("/contactUserSuccess")
    public String contactUserSuccess() {

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
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {
            Object attr = session.getAttribute("successContactUser");
            String messageContactUser = attr != null ? attr.toString() : "";

            if (!messageContactUser.isEmpty()) {
                session.removeAttribute("successContactUser");
                return "FrontEnd/Home/contactUserSuccess";
            }
            return "redirect:/contactususer";

        }

    }
}
