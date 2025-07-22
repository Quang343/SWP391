package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;
//
//import java.util.Date;
//import java.util.List;
//
//import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
//import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CheckOutProductsResponse;
//import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
//import com.example.AgriculturalWarehouseManagement.Backend.services.user.CheckOutUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import vn.payos.PayOS;
//import vn.payos.type.CheckoutResponseData;
//import vn.payos.type.ItemData;
//import vn.payos.type.PaymentData;
//
//@Controller
//public class PayosController {
//
//    @Autowired
//    private CheckOutUserService checkOutUserService;
//
//    @Autowired
//    private jakarta.servlet.http.HttpSession session;
//
//    private final PayOS payOS;
//
//    public PayosController(PayOS payOS) {
//        super();
//        this.payOS = payOS;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/create-payment-link", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public void checkout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
//
//        try {
//            final String baseUrl = getBaseUrl(request);
//
//            Object checkOutRequest = session.getAttribute("checkOutRequest");
//            Object account = session.getAttribute("account");
//            if (checkOutRequest == null || account == null) {
//                 httpServletResponse.sendRedirect("/cart");
//            }
//
//            UserResponse userResponse = (UserResponse) session.getAttribute("account") ;
//            CheckOutRequest cor = (CheckOutRequest) checkOutRequest;
//
//            final int totalPrice = (int) cor.getTotalPrice();
//            final String description = "Thanh toan don hang" + " " + userResponse.getEmail();
//
//
//            final String returnUrl = baseUrl + "/orderSuccess";
//            final String cancelUrl = baseUrl + "/cart";
//
//            // Gen order code
//            String currentTimeString = String.valueOf(new Date().getTime()); // 13 chữ số và xét theo mili giây kể từ thời điểm Unix (1970-01-01 00:00:00 UTC).
//            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6)); // xét index bắt đầu từ 0 (index : beginIndex -> 12)
//
//            ItemData item = ItemData.builder().quantity(1).price(totalPrice).build();
//            PaymentData paymentData = PaymentData.builder().orderCode(orderCode).amount(totalPrice).description(description)
//                    .returnUrl(returnUrl).cancelUrl(cancelUrl).item(item).build();
//
//            CheckoutResponseData data = payOS.createPaymentLink(paymentData);
//
//            String checkoutUrl = data.getCheckoutUrl();
//
//            httpServletResponse.setHeader("Location", checkoutUrl);
//            httpServletResponse.setStatus(302);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String getBaseUrl(HttpServletRequest request) {
//        String scheme = request.getScheme();  // "http" hoặc "https"
//        String serverName = request.getServerName();  // Tên máy chủ (ví dụ: localhost)
//        int serverPort = request.getServerPort();  // Cổng của server
//        String contextPath = request.getContextPath();  // Đường dẫn gốc của ứng dụng (ví dụ: /AgriculturalWarehouseManagement)
//
//        // Khởi tạo URL cơ bản
//        String url = scheme + "://" + serverName;
//
//        // Thêm cổng nếu không phải cổng mặc định
//        if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
//            url += ":" + serverPort;
//        }
//
//        if (!contextPath.startsWith("/")) {
//            contextPath = "/" + contextPath;
//        }
//
//        url += contextPath;
//
//        return url;
//    }
//}

import java.util.Date;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;

@Controller
public class PayosWalletsController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    private final PayOS payOS;

    public PayosWalletsController(PayOS payOS) {
        super();
        this.payOS = payOS;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/createPaymentWalletsLink", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void checkout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        try {
            final String baseUrl = getBaseUrl(request);

            Object checkOutRequest = session.getAttribute("checkOutRequestDeposit");
            Object account = session.getAttribute("account");

            if (checkOutRequest == null || account == null) {
                httpServletResponse.sendRedirect("/AgriculturalWarehouseManagementApplication/login");
                return;
            }

            UserResponse userResponse = (UserResponse) account;
            CheckOutRequest cor = (CheckOutRequest) checkOutRequest;
            int totalPrice = (int) cor.getTotalPrice();

            String currentTimeString = String.valueOf(new Date().getTime());
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

            String returnUrl = baseUrl + "/updateWalletsDeposit";
            String cancelUrl = baseUrl + "/cancelCheckOut";

            PaymentData paymentData = PaymentData.builder()
                    .orderCode(orderCode)
                    .buyerEmail(userResponse.getEmail())
                    .amount(totalPrice)
                    .description("Thanh toan don hang")
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .build();

            CheckoutResponseData data = payOS.createPaymentLink(paymentData);

            String checkoutUrl = data.getCheckoutUrl();

            httpServletResponse.setHeader("Location", checkoutUrl);
            httpServletResponse.setStatus(302);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        String url = scheme + "://" + serverName;
        if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
            url += ":" + serverPort;
        }
        url += contextPath;
        return url;
    }
}