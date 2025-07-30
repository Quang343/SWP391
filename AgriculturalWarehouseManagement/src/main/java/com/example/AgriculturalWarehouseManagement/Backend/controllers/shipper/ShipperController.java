package com.example.AgriculturalWarehouseManagement.Backend.controllers.shipper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


// @author: Đào Huy Hoàng

@Controller
public class ShipperController {

    @RequestMapping("/shipper")
    public String shipper() {
        return "BackEnd/Shipper/shipper";
    }

}

//INSERT INTO managerwarehouse.role (RoleID, RoleName, Status, description)
//VALUES (6, 'SHIPPER', 'Active', 'Người giao hàng');