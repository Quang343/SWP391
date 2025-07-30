package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WalletsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WalletsUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerApplication {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private WalletsUsersService walletsUsersService;

    @RequestMapping("/seller-application")
    public String sellerBecome(Model model) {

        Object account = session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        UserResponse userResponse = (UserResponse) account;

        WalletsResponse walletsResponse = walletsUsersService.getBalanceWallet(userResponse.getUserID());

        model.addAttribute("walletsResponse", walletsResponse);

        return "FrontEnd/Seller/seller-application";
    }
}
