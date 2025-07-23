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

    @RequestMapping("/login")
    public String login() {
        return "FrontEnd/Home/login";
    }

//    @RequestMapping("/seller")
//    public String seller() {
//        return "FrontEnd/Seller/Seller_Dashboard";
//    }




    @RequestMapping("/seller-become")
    public String sellerBecome() {
        return "FrontEnd/Seller/seller-become";
    }

//    @RequestMapping("/blog-grid")
//    public String bloggrid() {
//        return "FrontEnd/Home/blog-grid";
//    }


}
