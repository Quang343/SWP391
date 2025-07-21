package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/admin/orders")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.findByStatusIsNot(OrderStatus.CANCELLED.name());
        model.addAttribute("orders", orders);
        return "BackEnd/Admin/All_Orders";
    }

//    @GetMapping("/warehouse/addstockout")
//    public String showAddStockOutForm(Model model) {
//        List<Order> pendingOrders = orderService.findByStatus(OrderStatus.PENDING.name());
//        model.addAttribute("orders", pendingOrders);
//        return "BackEnd/Warehouse/addstockout";
//    }

    @PostMapping("/admin/delete_order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        boolean isDeleted = orderService.deleteById(id);
        if(isDeleted){
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/admin/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        boolean isUpdated = orderService.updateOrderStatus(status, id);
        if(isUpdated) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
