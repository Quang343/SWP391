package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderReviewUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderDetailUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderReviewUserService;
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
    private final OrderDetailUserService orderDetailUserService;
    private final OrderReviewUserService orderReviewUserService;

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

//    @GetMapping("/admin/orders/{orderId}/details")
//    public String getAllOrderDetailsByOrderId(@PathVariable("orderId") Long orderId,
//            Model model
//    ) {
//        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
//        Order order = orderService.findById(orderId);
//        model.addAttribute("orderDetails", orderDetails);
//        model.addAttribute("order", order);
//        return "BackEnd/Admin/OrderDetail";
//    }

    @GetMapping("/admin/orders/{orderId}/details")
    public String getAllOrderDetailsByOrderId(@PathVariable("orderId") Long orderId,
                                              Model model
    ) {
        Order order = orderService.findById(orderId);
        String orderCode = order.getOrderCode();
        ResponseResult<List<OrderDetailUserResponse>> orderDetailUserResponses = orderDetailUserService.getListOrderDetailsUserAndEmpty(orderCode);

        // View order
        OrderDetailUserResponse rightNavOrderDetailUserResponses = orderDetailUserResponses.getData().get(0);
        model.addAttribute("orderDetailUserResponses", orderDetailUserResponses.getData());
        model.addAttribute("rightNavOrderDetailUserResponses", rightNavOrderDetailUserResponses);

        // View orderReview
        ResponseResult<OrderReviewUserResponse> result = orderReviewUserService.getOrderReviewObject(orderCode);
        if (!result.isActive()) {
            model.addAttribute("messageOrderReview", result.getMessage());
            model.addAttribute("activeOrderReview", result.isActive());
            model.addAttribute("orderReviewUserResponse", result.getData());
        } else {
            model.addAttribute("messageOrderReview", result.getMessage());
            model.addAttribute("activeOrderReview", result.isActive());
            model.addAttribute("orderReviewUserResponse", result.getData());
        }

        ResponseResult<OrderReviewUserResponse> resultImage = orderReviewUserService.getOrderReviewObjectImage(orderCode);

        if (!resultImage.isActive()) {
            System.out.println("OrderReviewUserResponse: " + resultImage.isActive());
            model.addAttribute("activeOrderReviewImage", resultImage.isActive());
            model.addAttribute("orderReviewUserResponseImage", resultImage.getData());
        } else {
            model.addAttribute("activeOrderReviewImage", resultImage.isActive());
            model.addAttribute("orderReviewUserResponseImage", resultImage.getData());
        }
        model.addAttribute("order", order);
        return "BackEnd/Admin/OrderDetail";
    }



}
