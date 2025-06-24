package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;

    @RequestMapping("/admin/order_details")
    public String getAllOrderDetails(Model model) {
        List<OrderDetail> orderDetails = orderDetailService.findAll();
        model.addAttribute("orderDetails", orderDetails);
        return "BackEnd/Admin/OrderDetail";
    }

    @RequestMapping("/admin/orders/{orderId}/details")
    public String getAllOrderDetailsByOrderId(@PathVariable("orderId") Long orderId,
            Model model
    ) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        Order order = orderService.findById(orderId);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("order", order);
        return "BackEnd/Admin/OrderDetail";
    }

}
