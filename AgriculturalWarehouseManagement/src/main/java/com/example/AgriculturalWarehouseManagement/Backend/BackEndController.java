package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.BestSellerProductDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.NotificationRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.CategoryService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.order.OrderService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.product.ProductService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//import org.springframework.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BackEndController {

    private final NotificationRepository notificationRepository;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final OrderService orderService;

    private final UserService userService;

    private final NotificationService notificationService;

    @RequestMapping("/admin")
    public String admin(Model model, HttpSession session) {
        UserResponse userResponse = (UserResponse) session.getAttribute("accountAdmin");
        User currentUser = userService.findById(userResponse.getUserID() * 1L);
        //lấy ra 5 bản ghi đầu tiên
        Pageable pageable = PageRequest.of(0, 5);
        Page<Order> orderPage = orderService.getTopNRecentOrders(pageable);
        List<Order> orders = orderPage.getContent();

        List<Category> categories =  categoryService.findAll();
        List<BestSellerProductDTO> bestSeller = productService.getAllBestSellerProductDTO();

        List<Notification> notifications = notificationService.findTop10ByReceiverUserIdOrderByCreatedAtDesc(currentUser.getUserId());

        model.addAttribute("notifications", notifications);
        model.addAttribute("unreadCount", notificationService.countUnseenNotifications(currentUser.getUserId()));

        model.addAttribute("numberOfUsers", userService.getNumberOfUsers());
        model.addAttribute("numberOfOrders", orderService.getToTalOrderQuantity());
        model.addAttribute("numberOfProducts", productService.getProductQuantity());
        model.addAttribute("totalRevenue", orderService.getTotalRevenue());
        model.addAttribute("orders", orders);
        model.addAttribute("categories", categories);
        model.addAttribute("bestSeller", bestSeller);

        return "BackEnd/Admin/index";
    }

    @GetMapping("/admin/loadHeader")
    public String reloadHeader(Model model, HttpSession session) {
        UserResponse userResponse = (UserResponse) session.getAttribute("accountAdmin");
        User currentUser = userService.findById(userResponse.getUserID() * 1L);
        List<Notification> notifications = notificationService.findTop10ByReceiverUserIdOrderByCreatedAtDesc(currentUser.getUserId());

        model.addAttribute("notifications", notifications);
        model.addAttribute("unreadCount", notificationService.countUnseenNotifications(currentUser.getUserId()));

        // Trả về fragment
        return "BackEnd/fragments/Admin/header :: header";
    }


    @RequestMapping("/signup")
    public String signup() {
        return "BackEnd/signup";
    }

    @RequestMapping("/admin/order_tracking")
    public String orderTracking() {
        return "BackEnd/Admin/OrderTracking";
    }

    @RequestMapping("/warehouse")
    public String warehouse() {return "BackEnd/WareHouse/warehouseDashboard";}

    @RequestMapping("/warehouse/stockin")
    public String stockin() {return "BackEnd/WareHouse/stockin";}

    @RequestMapping("/warehouse/addstockin")
    public String addstockin() {return "BackEnd/WareHouse/addstockin";}

    @RequestMapping("/warehouse/stockout")
    public String stockout() {return "BackEnd/WareHouse/stockout";}

    @RequestMapping("/warehouse/addstockout")
    public String addstockout() {return "BackEnd/WareHouse/addstockout";}

    @RequestMapping("/warehouse/returnstockout")
    public String returnstockout() {return "BackEnd/WareHouse/return_stockout";}

    @RequestMapping("/warehouse/stockindetail")
    public String stockindetail() {return "BackEnd/WareHouse/stockin_detail";}

    @RequestMapping("/warehouse/stockoutdetail")
    public String stockoutdetail() {return "BackEnd/WareHouse/stockout_detail";}

    @RequestMapping("/warehouse/pruductbatch")
    public String pruductbatch() {return "BackEnd/WareHouse/productbatch";}

    @RequestMapping("/warehouse/supplier")
    public String supplier() {return "BackEnd/WareHouse/supplier";}

    @RequestMapping("/warehouse/addsupplier")
    public String addsupplier() {return "BackEnd/WareHouse/addsupplier";}

    @RequestMapping("/warehouse/productbatch")
    public String productbatch() {return "BackEnd/WareHouse/productbatch";}

    @RequestMapping("/warehouse/addproductbatch")
    public String addproductbatch() {return "BackEnd/WareHouse/addproductbatch";}

    @RequestMapping("/warehouse/adjustments")
    public String adjustment() {return "BackEnd/WareHouse/adjustment";}

    @RequestMapping("/warehouse/addadjustment")
    public String addadjustment() {return "BackEnd/WareHouse/addadjustment";}

//    @RequestMapping("/seller/orderTracking")
//    public String sellerOrderTracking() {
//        return "BackEnd/seller/order-tracking";
//    }


}

