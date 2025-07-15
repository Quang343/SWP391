package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderTrackingController {

    @GetMapping("/orderTracking")
    public String orderTracking() {
        return "FrontEnd/Home/orderTracking";
    }
}
