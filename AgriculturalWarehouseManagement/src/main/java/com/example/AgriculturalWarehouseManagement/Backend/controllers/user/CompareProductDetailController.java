package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CartUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CartUserService;
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

    @Autowired
    private CartUserService cartUserService;


    @Autowired
    private jakarta.servlet.http.HttpSession session;

    List<ProductDetailUserResponse> productDetailUserResponses = new ArrayList<>();

    @GetMapping("/compareProductDetail")
    public String compareProductDetail(Model model) {
        model.addAttribute("productDetailUserResponses", productDetailUserResponses);

        // View cart
        Object accountIdObj = session.getAttribute("accountId");
        if (accountIdObj != null) {
            int accountId = (int) accountIdObj;

            List<CartUserResponse> cartUserResponses = cartUserService.getCartByUserIds(accountId);

            int limit = Math.min(cartUserResponses.size(), 3);

            List<CartUserResponse> limitedCartUserResponses = cartUserResponses.subList(0, limit);

            model.addAttribute("sizeCart", cartUserResponses.size());
            model.addAttribute("cartUserResponses", limitedCartUserResponses);
            model.addAttribute("sizeCartBelow", Math.max(cartUserResponses.size() - 3, 0));


            double totalCart = 0;
            for (CartUserResponse cartUserResponse : cartUserResponses) {
                totalCart += cartUserResponse.getTotalPrice();
            }

            model.addAttribute("totalCart", totalCart);
        }
        return "FrontEnd/Home/compare";
    }

    @PostMapping("/compareProductDetail")
    public String compareProductDetail(@RequestParam(name = "productId") Integer productId,
                                       @RequestParam(name = "productDetailId", defaultValue = "-1", required = false) Integer productDetailId,
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
            if (productDetailId < 1) {
                model.addAttribute("errorCompare", "Sản phẩm chi tiết không có nên chưa thể so sánh");
            } else {

                if (productDetailUserResponse.getRemainQuantity() != 0) {
                    productDetailUserResponses.add(productDetailUserResponse);
                } else {
                    model.addAttribute("errorCompare", "Sản phẩm chi tiết không còn hàng nên chưa thể so sánh");
                }
            }

        }
        model.addAttribute("productDetailUserResponses", productDetailUserResponses);

        // View cart
        Object accountIdObj = session.getAttribute("accountId");
        if (accountIdObj != null) {
            int accountId = (int) accountIdObj;

            List<CartUserResponse> cartUserResponses = cartUserService.getCartByUserIds(accountId);

            int limit = Math.min(cartUserResponses.size(), 3);

            List<CartUserResponse> limitedCartUserResponses = cartUserResponses.subList(0, limit);

            model.addAttribute("sizeCart", cartUserResponses.size());
            model.addAttribute("cartUserResponses", limitedCartUserResponses);
            model.addAttribute("sizeCartBelow", Math.max(cartUserResponses.size() - 3, 0));


            double totalCart = 0;
            for (CartUserResponse cartUserResponse : cartUserResponses) {
                totalCart += cartUserResponse.getTotalPrice();
            }

            model.addAttribute("totalCart", totalCart);
        }
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
        return "redirect:/compareProductDetail";
    }
}
