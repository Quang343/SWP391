package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.Order_SellerService;

@Controller
@RequiredArgsConstructor
public class OrderTrackingController {

    private final Order_SellerService order_SellerService;

    // Thêm @GetMapping để Spring biết ánh xạ URL này
    @GetMapping("/seller/orderTracking")
    public String sellerOrderTracking(@RequestParam("orderId") Long orderId, Model model) {
        try {
            Order_SellerDTO order = order_SellerService.getOrderById(orderId);
            model.addAttribute("order", order);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "BackEnd/seller/order-tracking"; // render trang tracking
    }
}

