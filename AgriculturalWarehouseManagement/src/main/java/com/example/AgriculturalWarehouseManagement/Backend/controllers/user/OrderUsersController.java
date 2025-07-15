package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CheckOutResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class OrderUsersController {

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private OrderUsersService orderUsersService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    CheckOutRequest checkOutRequestGlobal = new CheckOutRequest();
    int accountIdGlobal = 0;

    @GetMapping("/checkSuccess")
    public String orderSucess(@RequestParam(name = "orderCode", defaultValue = "0") long orderCode, Model model) {
        if (checkOutRequestGlobal.getTotalPrice() == 0.00) {
            System.out.println("checkOutRequest is null1");
            return "redirect:/home";
        }

        ResponseResult<CheckOutResponse> checkOutResponseResponseResult = orderUsersService.insertOrderAndOrderDetail(checkOutRequestGlobal, orderCode, accountIdGlobal);
        if (!checkOutResponseResponseResult.isActive()) {
        System.out.println("accountIdGlobal = " + accountIdGlobal);
            return "redirect:/cart";
        }

        session.removeAttribute("errorMessageCheckOut");
        return "redirect:/successCheckOut?orderCode=" + orderCode;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/validateCheckOut", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String validateCheckOut(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @ModelAttribute CheckOutRequest checkOutRequest,
                                   Model model) throws IOException, ServletException {

        ResponseResult<CheckOutResponse> checkOutResponseResponseResult = orderUsersService.checkValidateCheckOut(checkOutRequest);
        if (!checkOutResponseResponseResult.isActive()) {
            if (checkOutRequest.getVoucherId() == 0) {
                session.setAttribute("errorMessageCheckOut", checkOutResponseResponseResult.getMessage());
                return "redirect:/checkOut";
            } else {
                session.setAttribute("errorMessageCheckOut", checkOutResponseResponseResult.getMessage());
                return "redirect:/checkOut?voucherId=" + checkOutRequest.getVoucherId();
            }
        }

        checkOutRequestGlobal = checkOutRequest;
        Object accountIdObj = session.getAttribute("accountId");
        accountIdGlobal = (int) accountIdObj;

        session.setAttribute("checkOutRequest", checkOutRequest);
        request.getRequestDispatcher("/createPaymentLink").forward(request, response);

        return "forward:/home";
    }

}
