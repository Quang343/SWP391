package com.example.AgriculturalWarehouseManagement.Backend;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackEndController {

    @RequestMapping("/admin")
    public String admin() {return "BackEnd/Admin/index";}

//    @RequestMapping("/")
//    public String home(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        return "FrontEnd/Home/home";
//    }

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

    @RequestMapping("/warehouse/adjustment")
    public String adjustment() {return "BackEnd/WareHouse/adjustment";}

    @RequestMapping("/warehouse/addadjustment")
    public String addadjustment() {return "BackEnd/WareHouse/addadjustment";}

    @RequestMapping("/seller-dashboard")
    public String sellerDashboard() {
        return "BackEnd/Seller/seller-dashboard";
    }

}

