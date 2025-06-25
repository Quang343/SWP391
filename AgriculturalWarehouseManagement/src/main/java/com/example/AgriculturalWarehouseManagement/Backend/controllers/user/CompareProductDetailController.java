package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.ProductDetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CompareProductDetailController {

    @Autowired
    private ProductDetailUserService productDetailUserService;

    List<ProductDetailUserResponse> productDetailUserResponses = new ArrayList<>();

    @GetMapping("/compareProductDetail")
    public String compareProductDetail(Model model) {
        return "FrontEnd/Home/compare";
    }

    @PostMapping("/compareProductDetail")
    public String compareProductDetail(@RequestParam(name = "productId") Integer productId,
                                       @RequestParam(name = "productDetailId") Integer productDetailId,
                                       Model model) {

        ProductDetailUserResponse productDetailUserResponse = productDetailUserService.getProductDetailUsers(productDetailId);
        boolean exist = true;
        for (ProductDetailUserResponse productDetailUserResponse1 : productDetailUserResponses) {
            if (productDetailUserResponse1.getProductDetailId() == productDetailId) {
                exist = false;
                break;
            }
        }
        if (exist) {
            productDetailUserResponses.add(productDetailUserResponse);
        }
        model.addAttribute("productDetailUserResponses", productDetailUserResponses);
        return "FrontEnd/Home/compare";
    }

    @PostMapping("/deleteCompareProductDetail")
    public String deleteCompareProductDetail(@RequestParam(name = "productDetailId") Integer productDetailId,
                                             Model model) {

        Iterator<ProductDetailUserResponse> iterator = productDetailUserResponses.iterator();
        while (iterator.hasNext()) {
            ProductDetailUserResponse productDetailUserResponse1 = iterator.next();
            if (productDetailUserResponse1.getProductDetailId() == (productDetailId)) {
                iterator.remove();
            }
        }

        model.addAttribute("productDetailUserResponses", productDetailUserResponses);
        return "FrontEnd/Home/compare";
    }
}
