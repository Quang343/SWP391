package com.example.AgriculturalWarehouseManagement.services.order;

import com.example.AgriculturalWarehouseManagement.dtos.OrderDTO;
import com.example.AgriculturalWarehouseManagement.models.Order;
import com.example.AgriculturalWarehouseManagement.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.models.User;
import com.example.AgriculturalWarehouseManagement.models.Voucher;
import com.example.AgriculturalWarehouseManagement.repositories.OrderRepository;
import com.example.AgriculturalWarehouseManagement.repositories.UserRepository;
import com.example.AgriculturalWarehouseManagement.repositories.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final Random random = new Random();

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .user(user)
                .orderDate(orderDTO.getOrderDate())
                .status(orderDTO.getStatus())
                .totalAmount(orderDTO.getTotalAmount())
                .fullName(orderDTO.getFullName())
                .email(orderDTO.getEmail())
                .phone(orderDTO.getPhone())
                .address(orderDTO.getAddress())
                .note(orderDTO.getNote())
                .orderCode(generateUniqueOrderCode())
                .build();

        BigDecimal discount = BigDecimal.ZERO;

        Optional<Voucher> optionalVoucher = voucherRepository.findById(orderDTO.getVoucherId());
        if (optionalVoucher.isPresent()) {
            Voucher voucher = optionalVoucher.get();
            order.setVoucher(voucher);
            order.setVoucherCode(voucher.getVoucherCode());
            discount = calculateDiscount(voucher, orderDTO.getTotalAmount());
        }
        order.setDiscountAmount(discount);
        order.setFinalAmount(order.getTotalAmount().subtract(order.getDiscountAmount()));
        orderRepository.save(order);
        return order;
    }

    private BigDecimal calculateDiscount(Voucher voucher, BigDecimal orderAmount) {
        if (voucher.getDiscountType().name().equalsIgnoreCase("PERCENT")) {
            return orderAmount.multiply(voucher.getDiscountValue().divide(BigDecimal.valueOf(100)));
        } else {
            return voucher.getDiscountValue();
        }
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatusIsNot(String status) {
        return orderRepository.findByStatusIsNot(status);
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setStatus(OrderStatus.REMOVED.name());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrderStatus(String status, Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistsByOrderCode(String orderCode) {
        return orderRepository.existsByOrderCode(orderCode);
    }

    public String generateUniqueOrderCode() {
        String code;
        do {
            int randomNumber = 1000000 + random.nextInt(9000000); // 7 chữ số
            code = String.format("#ORD%d", randomNumber);
        } while (orderRepository.existsByOrderCode(code));
        return code;
    }
}
