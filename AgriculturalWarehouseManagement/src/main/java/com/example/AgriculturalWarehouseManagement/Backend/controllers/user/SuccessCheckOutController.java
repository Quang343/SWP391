package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuccessCheckOutController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserCustomerService userCustomerService;

    @GetMapping("/successCheckOut")
    public String cancelCheckOut(@RequestParam(name = "orderCode", defaultValue = "215732") long orderCode,
                                 Model model) {
//        if (orderCode == 0) {
//            return "redirect:/home";
//        }
        model.addAttribute("orderCode",orderCode);
        return "FrontEnd/Home/orderSuccess";
    }
}
