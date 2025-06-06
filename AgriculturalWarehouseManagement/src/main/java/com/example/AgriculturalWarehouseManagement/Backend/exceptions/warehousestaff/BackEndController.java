package com.example.AgriculturalWarehouseManagement.Backend.exceptions.warehousestaff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackEndController {

    @RequestMapping("/admin")
    public String admin() {
        return "BackEnd/Admin/index";
    }

//    @RequestMapping("")
//    public String home() {
//        return "/home";
//    }
//
//    @RequestMapping("/signup")
//    public String signup() {
//        return "BackEnd/signup";
//    }

    @RequestMapping("/admin/category")
    public String adminCategory() {
        return "BackEnd/Admin/Category";
    }

    @RequestMapping("/admin/user")
    public String adminUser() {
        return "BackEnd/Admin/All_user";
    }
    @RequestMapping("/admin/product")
    public String adminProduct() {
        return "BackEnd/Admin/All_Product";
    }
    @RequestMapping("/admin/addproduct")
    public String adminAddProduct() {
        return "BackEnd/Admin/Add_Product";
    }

    @RequestMapping("/admin/adduser")
    public String adminAddUser() {
        return "BackEnd/Admin/Add_User";
    }
    @RequestMapping("backend/login")
    public String login() {
        return "BackEnd/Admin/login";
    }
    @RequestMapping("/admin/addcategory")
    public String addCategory() {
        return "BackEnd/Admin/Add_Category";
    }

    @RequestMapping("/admin/Admin/orderlist")
    public String orderList() {
        return "BackEnd/Admin/OrderList";
    }
    @RequestMapping("/admin/Admin/orderdetail")
    public String orderDetail() {
        return "BackEnd/Admin/OrderDetail";
    }
    @RequestMapping("/admin/Admin/ordertracking")
    public String orderTracking() {
        return "BackEnd/Admin/OrderTracking";
    }
    @RequestMapping("/warehouse")
    public String warehouse() {return "BackEnd/WareHouse/index";}
    @RequestMapping("/warehouse/stockin")
    public String stockin() {return "BackEnd/WareHouse/stockin";}
    @RequestMapping("/warehouse/stockout")
    public String stockout() {return "BackEnd/WareHouse/stockout";}
    @RequestMapping("/warehouse/stockindetail")
    public String stockindetail() {return "BackEnd/WareHouse/stockin_detail";}
    @RequestMapping("/warehouse/stockoutdetail")
    public String stockoutdetail() {return "BackEnd/WareHouse/stockout_detail";}
    @RequestMapping("/warehouse/pruductbatch")
    public String pruductbatch() {return "BackEnd/WareHouse/productbatch";}
    @RequestMapping("/warehouse/supplier")
    public String supplier() {return "BackEnd/WareHouse/supplier";}

}

