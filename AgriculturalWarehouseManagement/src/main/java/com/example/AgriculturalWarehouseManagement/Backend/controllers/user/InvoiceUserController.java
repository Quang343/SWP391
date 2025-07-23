package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderDetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InvoiceUserController {

    @Autowired
    private OrderDetailUserService orderDetailUserService;

    @GetMapping("/invoiceUser")
    public String invoiceUser(@RequestParam(name= "orderCode", defaultValue = "0") String orderCode,
                              Model model) {
        System.out.println("HELLO"+orderCode);
        ResponseResult<List<OrderDetailUserResponse>> orderDetailUserResponses = orderDetailUserService.getListOrderDetailsUserAndEmpty(orderCode);
        if (!orderDetailUserResponses.isActive()) {
            return "redirect:/home";
        }
        OrderDetailUserResponse rightNavOrderDetailUserResponses = orderDetailUserResponses.getData().get(0);
        model.addAttribute("orderDetailUserResponses", orderDetailUserResponses.getData());
        model.addAttribute("rightNavOrderDetailUserResponses", rightNavOrderDetailUserResponses);
        return "FrontEnd/Home/invoice";
    }
}
