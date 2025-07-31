package com.example.AgriculturalWarehouseManagement.Backend.controllers.shipper;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.bloger.UserServiceBlog;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.User_SellerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// @author: Đào Huy Hoàng

@Controller
@RequiredArgsConstructor
public class ShipperController {

    @RequestMapping("/shipper")
    public String shipper() {
        return "BackEnd/Shipper/shipper";
    }

//    private final UserServiceBlog userServiceBlog;
//
//    @RequestMapping("/shipper")
//    public String shipperPage(HttpSession session, Model model) {
//        Object sessionAccount = session.getAttribute("account");
//        if (sessionAccount instanceof UserResponse userResponse) {
//            User user = userServiceBlog.findById(userResponse.getUserID());
//            if (user == null) {
//                return "redirect:/login";
//            }
//            model.addAttribute("shipper", user);
//            return "BackEnd/Shipper/shipper";
//        }
//        return "redirect:/login";
//    }

}

//INSERT INTO managerwarehouse.role (RoleID, RoleName, Status, description)
//VALUES (6, 'SHIPPER', 'Active', 'Người giao hàng');