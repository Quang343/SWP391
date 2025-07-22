package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CheckOutResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WalletsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderUsersService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WalletsUsersService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@Controller
public class OrderUsersController {

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private OrderUsersService orderUsersService;

    @Autowired
    private WalletsUsersService walletsUsersService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    CheckOutRequest checkOutRequestGlobal = new CheckOutRequest();
    int accountIdGlobal = 0;

    @GetMapping("/checkSuccess")
    public String orderSucess(@RequestParam(name = "orderCode", defaultValue = "0") long orderCode, Model model) {
        if (checkOutRequestGlobal.getTotalPrice() == 0.00) {
            return "redirect:/home";
        }

        ResponseResult<CheckOutResponse> checkOutResponseResponseResult = orderUsersService.insertOrderAndOrderDetail(checkOutRequestGlobal, orderCode, accountIdGlobal);
        if (!checkOutResponseResponseResult.isActive()) {
            return "redirect:/cart";
        }

        accountIdGlobal = 0;
        checkOutRequestGlobal.setTotalPrice(0.00);

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
            session.setAttribute("errorMessageCheckOut", checkOutResponseResponseResult.getMessage());
            if (checkOutRequest.getVoucherId() == 0) {
                return "redirect:/checkOut";
            }

            return "redirect:/checkOut?voucherId=" + checkOutRequest.getVoucherId();
        }

        checkOutRequestGlobal = checkOutRequest;
        Object accountIdObj = session.getAttribute("accountId");
        accountIdGlobal = (int) accountIdObj;

        session.setAttribute("checkOutRequest", checkOutRequest);
        request.getRequestDispatcher("/createPaymentLink").forward(request, response);

        return "forward:/home";
    }

    @PostMapping("/validateCheckOutByWallet")
    public String validateCheckOutByWallet(@ModelAttribute CheckOutRequest checkOutRequest, Model model) {


        // Kiểm tra hợp lệ đơn hàng
        ResponseResult<CheckOutResponse> checkOutValidation = orderUsersService.checkValidateCheckOut(checkOutRequest);

        if (!checkOutValidation.isActive()) {
            session.setAttribute("errorMessageCheckOut", checkOutValidation.getMessage());
            session.setAttribute("saveAddress", checkOutRequest.getAddressId());

            return checkOutRequest.getVoucherId() == 0
                    ? "redirect:/checkOut"
                    : "redirect:/checkOut?voucherId=" + checkOutRequest.getVoucherId();
        }


        // Kiểm tra ví
        Object accountObj = session.getAttribute("account");
        if (accountObj instanceof UserResponse account) {
            System.out.println("hello" + checkOutRequest.getAddressId());
            session.setAttribute("saveAddress", checkOutRequest.getAddressId());
            ResponseResult<WalletsResponse> walletCheck = walletsUsersService.checkWalletUser(account.getUserID(), checkOutRequest);

            if (!walletCheck.isActive()) {
                session.setAttribute("errorMessageWallets", walletCheck.getMessage());
                return checkOutRequest.getVoucherId() == 0
                        ? "redirect:/checkOut"
                        : "redirect:/checkOut?voucherId=" + checkOutRequest.getVoucherId();
            }
        }


        // Thêm vào data nếu thành công
        String currentTimeString = String.valueOf(new Date().getTime()); // 13 chữ số và xét theo mili giây kể từ thời điểm Unix (1970-01-01 00:00:00 UTC).
        long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6)); // xét index bắt đầu từ 0 (index : beginIndex -> 12)
        checkOutRequestGlobal = checkOutRequest;

        Object accountIdObj = session.getAttribute("accountId");
        accountIdGlobal = (int) accountIdObj;

        ResponseResult<CheckOutResponse> checkOutResponseResponseResult = orderUsersService.insertOrderAndOrderDetail(checkOutRequestGlobal, orderCode, accountIdGlobal);

        if (!checkOutResponseResponseResult.isActive()) {
            return "redirect:/cart";
        }


        walletsUsersService.updateWalletAfterOrder(accountIdGlobal, checkOutRequestGlobal);

        // Thành công
        return "redirect:/successCheckOut?orderCode=" + orderCode;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/depositWallet", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deposit(@RequestParam(name = "totalPrice", defaultValue = "0", required = false) double totalPrice,
                          HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        CheckOutRequest checkOutRequest = new CheckOutRequest();
        checkOutRequest.setTotalPrice(totalPrice);
        session.setAttribute("checkOutRequestDeposit", checkOutRequest);
        checkOutRequestGlobal = checkOutRequest;

        Object accountIdObj = session.getAttribute("accountId");
        accountIdGlobal = (int) accountIdObj;

        request.getRequestDispatcher("/createPaymentWalletsLink").forward(request, response);
        return "forward:/home";
    }

    @GetMapping("/updateWalletsDeposit")
    public String updateWalletDeposit(@RequestParam(name = "orderCode", defaultValue = "0") long orderCode) {

        if (checkOutRequestGlobal.getTotalPrice() != 0.00 && accountIdGlobal != 0) {
            System.out.println("hello"+checkOutRequestGlobal.getTotalPrice());
            walletsUsersService.updateWalletsDeposit(accountIdGlobal, checkOutRequestGlobal);

            accountIdGlobal = 0;
            checkOutRequestGlobal.setTotalPrice(0.00);
            return "redirect:/checkSuccessDeposit";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/checkSuccessDeposit")
    public String checkSuccessDeposite(@ModelAttribute CheckOutRequest checkOutRequest, Model model) {
        return "FrontEnd/Home/depositWallet";
    }

}
