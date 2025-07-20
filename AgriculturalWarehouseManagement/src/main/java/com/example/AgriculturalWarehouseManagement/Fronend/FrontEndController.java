package com.example.AgriculturalWarehouseManagement.Fronend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    @RequestMapping("/shop")
    public String shop() {
        return "FrontEnd/Home/shop";
    }

    @RequestMapping("/shop/Home/productdetail")
    public String productdetail() {
        return "FrontEnd/Home/productdetail";
    }


    @RequestMapping("/seller")
    public String seller() {
        return "FrontEnd/Seller/seller";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "FrontEnd/Home/cart";
    }



}
