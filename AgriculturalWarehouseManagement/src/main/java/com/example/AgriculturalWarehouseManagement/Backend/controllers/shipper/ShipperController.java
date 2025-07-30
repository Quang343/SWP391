package com.example.AgriculturalWarehouseManagement.Backend.controllers.shipper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShipperController {

    @RequestMapping("/shipper")
    public String shipper() {
        return "BackEnd/Shipper/shipper";
    }

}
