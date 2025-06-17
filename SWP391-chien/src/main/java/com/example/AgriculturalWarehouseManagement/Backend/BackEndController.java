package com.example.AgriculturalWarehouseManagement.Backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackEndController {

    @RequestMapping("/admin")
    public String admin() {
        return "BackEnd/Admin/index";
    }

    @RequestMapping("")
    public String home() {
        return "/home";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "BackEnd/signup";
    }



    @RequestMapping("/admin/orderlist")
    public String orderList() {
        return "BackEnd/Admin/OrderList";
    }
    @RequestMapping("/admin/orderdetail")
    public String orderDetail() {
        return "BackEnd/Admin/OrderDetail";
    }
    @RequestMapping("/admin/ordertracking")
    public String orderTracking() {
        return "BackEnd/Admin/OrderTracking";
    }
}

