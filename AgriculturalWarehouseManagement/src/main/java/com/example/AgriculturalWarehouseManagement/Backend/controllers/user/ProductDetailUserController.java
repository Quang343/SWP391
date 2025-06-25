package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductDetailUserController {

    @GetMapping("/productDetail")
    public String productDetail(Model model) {
        return"FrontEnd/Home/productDetail";
    }
}
