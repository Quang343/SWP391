package com.example.AgriculturalWarehouseManagement.Backend.services.shipper;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.Order_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.shipper.OrderShipperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShipperService {

    private final OrderShipperRepository orderRepository;

    /**
     * Lấy tất cả đơn hàng trạng thái STOCKOUT (đang chờ shipper giao)
     */
    public List<Order_SellerDTO> getDeliveredOrdersForShipper() {
        List<Order> orders = orderRepository.findByStatus("STOCKOUT");
        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Lấy chi tiết một đơn hàng cụ thể theo ID
     */
    public Order_SellerDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng với ID: " + orderId));
        return convertToDTO(order);
    }

    /**
     * Xác nhận đơn hàng đã giao thành công → STOCKOUT → DELIVERED
     */
    public void confirmDelivered(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        if (!"STOCKOUT".equals(order.getStatus())) {
            throw new IllegalStateException("Chỉ có thể xác nhận đơn hàng đang ở trạng thái STOCKOUT");
        }

        order.setStatus("DELIVERED");
        orderRepository.save(order);
    }

    /**
     * Huỷ đơn hàng (bom hàng) → giữ nguyên STOCKOUT
     */
    public void cancelDelivery(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        if (!"STOCKOUT".equals(order.getStatus())) {
            throw new IllegalStateException("Chỉ có thể huỷ đơn hàng đang ở trạng thái STOCKOUT");
        }

        // Không thay đổi trạng thái, giữ nguyên STOCKOUT
        orderRepository.save(order);
    }

    /**
     * Chuyển Order entity sang DTO để trả về FE
     */
    private Order_SellerDTO convertToDTO(Order order) {
        Order_SellerDTO dto = new Order_SellerDTO();
        dto.setOrderId(order.getId());
        dto.setOrderCode(order.getOrderCode());
        dto.setPhone(order.getPhone());
        dto.setAddress(order.getAddress());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setFinalAmount(order.getFinalAmount());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
