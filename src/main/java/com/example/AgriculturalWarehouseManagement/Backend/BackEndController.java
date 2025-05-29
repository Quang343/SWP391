package com.example.AgriculturalWarehouseManagement.Backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BackEndController {

    @RequestMapping("/admin")
    public String admin() {
        return "BackEnd/index";
    }

    @RequestMapping("")
    public String home() {
        return "/home";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "BackEnd/signup";
    }

    @RequestMapping("/admin/category")
    public String adminCategory() {
        return "BackEnd/Category";
    }

    @RequestMapping("/admin/user")
    public String adminUser() {
        return "BackEnd/All_user";
    }
    @RequestMapping("/admin/product")
    public String adminProduct() {
        return "BackEnd/All_Product";
    }


}

