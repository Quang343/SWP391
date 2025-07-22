package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        if (orderId == null) {
            throw new RuntimeException("OrderID cannot be null");
        }
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No OrderDetails found for orderID: " + orderId);
        }
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream().map(orderDetail -> {
            if (orderDetail.getId() == null) {
                throw new RuntimeException("OrderDetail ID cannot be null for orderID: " + orderId);
            }
            if (orderDetail.getProductDetailId() == null) {
                throw new RuntimeException("ProductDetailId cannot be null for OrderDetail ID: " + orderDetail.getId());
            }
            OrderDetailDTO dto = new OrderDetailDTO();
            dto.setOrderId(orderDetail.getOrder().getId());
            dto.setProductDetailId(orderDetail.getProductDetailId());
            dto.setQuantity(orderDetail.getQuantity());
            dto.setPrice(orderDetail.getPrice());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailDTOs);
    }

    @GetMapping("/admin/orders/{orderId}/details")
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
