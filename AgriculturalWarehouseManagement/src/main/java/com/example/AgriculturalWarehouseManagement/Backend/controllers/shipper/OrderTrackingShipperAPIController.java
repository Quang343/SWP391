package com.example.AgriculturalWarehouseManagement.Backend.controllers.shipper;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.services.shipper.OrderShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@author: Đào Huy Hoàng

@RestController
@RequestMapping("/api/shipper/orders")
@RequiredArgsConstructor
public class OrderTrackingShipperAPIController {

    private final OrderShipperService shipperService;

    /**
     * Lấy tất cả đơn hàng có trạng thái STOCKOUT
     */
    @GetMapping
    public ResponseEntity<List<Order_SellerDTO>> getAllDeliveredOrders() {
//        List<Order_SellerDTO> orders = shipperService.getDeliveredOrdersForShipper();
        List<Order_SellerDTO> orders = shipperService.getAllOrdersForShipper();
        return ResponseEntity.ok(orders);
    }

    /**
     * Lấy chi tiết một đơn hàng cụ thể
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order_SellerDTO> getOrderById(@PathVariable Long orderId) {
        Order_SellerDTO dto = shipperService.getOrderById(orderId);
        return ResponseEntity.ok(dto);
    }

    /**
     * Xác nhận đã giao hàng thành công
     */
    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<String> confirmDelivered(@PathVariable Long orderId) {
        shipperService.confirmDelivered(orderId);
        return ResponseEntity.ok("Đã xác nhận giao hàng thành công.");
    }

    /**
     * Hủy đơn vì bị bom hàng
     */
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelDelivery(@PathVariable Long orderId) {
        shipperService.cancelDelivery(orderId);
        return ResponseEntity.ok("Đơn hàng đã được huỷ.");
    }

    @GetMapping("/count-stockout")
    public long countStockoutOrders() {
        return shipperService.countStockoutOrders();
    }

    @GetMapping("/count-delivered")
    public long countDeliveredOrders() {
        return shipperService.countDeliveredOrders();
    }
}
