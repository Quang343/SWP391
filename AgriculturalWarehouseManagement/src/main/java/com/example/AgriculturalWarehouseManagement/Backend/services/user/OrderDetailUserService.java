package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.WeightCompareProductDetailsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailUserService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Get orderDetail
    public List<OrderDetailUserResponse> getOrderDetails(String orderCode) {
        List<Map<String, Object>> raw = orderDetailRepository.rawGetOrderDetails(orderCode);

        return raw.stream().map(row -> {
            // Dựa vào tên cột trong Map và ánh xạ vào các thuộc tính của OrderDetailUserResponse
            return new OrderDetailUserResponse(
                    ((Long) row.get("orderid")).intValue(),       // orderId
                    (String) row.get("addressShip"),    // addressShip
                    ((BigDecimal) row.get("discountAmount")).doubleValue(), // discountAmount
                    (String) row.get("emailShip"),     // emailShip
                    ((BigDecimal) row.get("finalAmount")).doubleValue(),    // finalAmount
                    (String) row.get("fullNameShip"),  // fullNameShip
                    (String) row.get("noteShip"),      // noteShip
                    (String) row.get("ordercode"),     // orderCode
                    ((Timestamp) row.get("orderdate")).toLocalDateTime(), // orderDate
                    (String) row.get("phoneShip"),     // phoneShip
                    (String) row.get("orderStatus"),   // orderStatus
                    ((BigDecimal) row.get("totalamount")).doubleValue(),   // totalAmount
                    (String) row.get("voucherCode"),   // voucherCode
                    ((Long) row.get("productdetailid")).intValue(), // productDetailId
                    (String) row.get("ProductName"),   // productName
                    ((BigDecimal) row.get("productTotalPrice")).doubleValue(), // productTotalPrice
                    (Integer) row.get("quantity"),     // quantity
                    (String) row.get("sellerName"),    // sellerName
                    (String) row.get("productWeight"), // productWeight
                    ((Double) row.get("price")).doubleValue(),
                    (String) row.get("imageurl"),      // imageUrl
                    ((Integer) row.get("productID")).intValue(), // productId
                    (String) row.get("userUseName"),   // userUseName
                    (String) row.get("userEmail"),     // userEmail
                    (String) row.get("userAddress"),   // userAddress
                    (String) row.get("userPhone")      // userPhone
            );
        }).collect(Collectors.toList());
    }



    public ResponseResult<List<OrderDetailUserResponse>> getListOrderDetailsUserAndEmpty(String orderCode) {

        if (orderCode == null || orderCode.isEmpty()) {
            return new ResponseResult<>("ERROR", "Hãy nhập vào Order code", false);
        }

        if (getOrderDetails(orderCode).isEmpty()) {
            return new ResponseResult<>("ERROR", "Order code không tồn tại", false);
        } else {
            List<OrderDetailUserResponse> orderDetailUserResponses = getOrderDetails(orderCode);
            for (OrderDetailUserResponse orderDetailUserResponse : orderDetailUserResponses) {
                System.out.println(orderDetailUserResponse.toString());
            }
            return new ResponseResult<>("SUCCESS","Xem chi tiếng Order thành công", true, orderDetailUserResponses);
        }
    }
}
