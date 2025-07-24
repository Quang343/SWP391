package com.example.AgriculturalWarehouseManagement.Backend.services.admin.order;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.OrderDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.OrderDTO_WareHouse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final Random random = new Random();

    public List<OrderDTO_WareHouse> findByStatus(String status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO_WareHouse> findAllForStockOut() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public OrderDTO_WareHouse findDtoById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        return convertToDTO(order);
    }

    private OrderDTO_WareHouse convertToDTO(Order order) {
        OrderDTO_WareHouse dto = new OrderDTO_WareHouse();
        dto.setOrderId(order.getId());
        dto.setOrderCode(order.getOrderCode());
        dto.setOrderDate(order.getOrderDate());
        dto.setFullName(order.getFullName());
        dto.setPhone(order.getPhone());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setFinalAmount(order.getFinalAmount());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setEmail(order.getEmail());
        dto.setAddress(order.getAddress());
        dto.setNote(order.getNote());

        if (order.getUser() != null) {
            dto.setUserId(order.getUser().getUserId());
        }

        if (order.getVoucher() != null) {
            dto.setVoucherId(order.getVoucher().getId());
            dto.setVoucherCode(order.getVoucher().getVoucherCode());
        }

        return dto;
    }




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
            order.setStatus(OrderStatus.CANCELLED.name());
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

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
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
