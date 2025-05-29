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
    @RequestMapping("/admin/products")
    public String adminProduct() {
        return "BackEnd/All_Product";
    }

    @RequestMapping("/admin/add_product")
    public String addProduct() {
        return "BackEnd/Add_Product";
    }

    @RequestMapping("/admin/adduser")
    public String adminAddUser() {
        return "BackEnd/Add_User";
    }
    @RequestMapping("backend/login")
    public String login() {
        return "BackEnd/login";
    }
    @RequestMapping("/admin/addcategory")
    public String addCategory() {
        return "BackEnd/Add_Category";
    }

    @RequestMapping("/admin/orderlist")
    public String orderList() {
        return "BackEnd/OrderList";
    }
    @RequestMapping("/admin/orderdetail")
    public String orderDetail() {
        return "BackEnd/OrderDetail";
    }
    @RequestMapping("/admin/ordertracking")
    public String orderTracking() {
        return "BackEnd/OrderTracking";
    }
}

