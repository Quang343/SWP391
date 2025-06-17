package com.example.AgriculturalWarehouseManagement.Backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VoucherController {

    @GetMapping("/admin/vouchers")
    public String getAllVouchers() {
        return "BackEnd/Admin/All_Coupons";
    }
}
