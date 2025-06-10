package com.example.AgriculturalWarehouseManagement.Fronend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {
    @RequestMapping("/home")
    public String home() {
        return "FrontEnd/Home/home";
    }

    @RequestMapping("/shop")
    public String shop() {
        return "FrontEnd/Home/shop";
    }

    @RequestMapping("/shop/Home/productdetail")
    public String productdetail() {
        return "FrontEnd/Home/productdetail";
    }

//    @RequestMapping("/login")
//    public String login() {
//        return "FrontEnd/Home/login";
//    }

    @RequestMapping("/seller")
    public String seller() {
        return "FrontEnd/Seller/seller";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "FrontEnd/Home/cart";
    }

    @RequestMapping("/blog-list")
    public String bloglist() {
        return "FrontEnd/Home/blog-list";
    }
   @RequestMapping("/blog-detail")
    public String blogdetail(){
        return "FrontEnd/Home/blog-detail";
    }

    @RequestMapping("/blog-grid")
    public String bloggrid(){
        return "FrontEnd/Home/blog-grid" ;
    }


}
