package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.models.*;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderUsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CheckOutUserService checkOutUserService;

    @Autowired
    private CartUserService cartUserService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderReviewRepository orderReviewRepository;

    @Autowired
    private WalletsRepository walletsRepository;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    public ResponseResult<CheckOutResponse> insertOrderAndOrderDetail(CheckOutRequest checkOutRequest, long orderCode, int accountId) {

        if (checkOutRequest == null || (checkOutRequest.getAddressId() == 0 && checkOutRequest.getSubTotal() == 0.0 &&
                checkOutRequest.getVoucherId() == 0 &&
                checkOutRequest.getPercentDiscount() == 0.0 &&
                checkOutRequest.getTotalPrice() == 0.0)) {
            return new ResponseResult<>("ERROR", "Không tìm thấy request", false);
        } else {
            if (checkOutRequest.getAddressId() == 0) {
                return new ResponseResult<>("ERROR", "Hãy thêm thông tin địa chỉ để đặt hàng", false);
            }
            Optional<User> user = userRepository.findByUserIdNative(accountId);
            System.out.println("accountIdGlobal1 = " + accountId);
            MyAddressBook myAddressBook = addressRepository.findById((long) checkOutRequest.getAddressId()).get();

            if (checkOutRequest.getVoucherId() == 0) {
                Order order = new Order();
                order.setAddress(myAddressBook.getAddress());
                order.setDiscountAmount(BigDecimal.valueOf(0.00));
                order.setEmail(myAddressBook.getEmail());
                order.setFinalAmount(BigDecimal.valueOf(checkOutRequest.getTotalPrice()));
                order.setFullName(myAddressBook.getFullName());
                order.setNote(myAddressBook.getNote() + " (" + myAddressBook.getSetToAddress() + ")");
                order.setOrderCode(String.valueOf(orderCode));
                order.setOrderDate(LocalDateTime.now());
                order.setPhone(myAddressBook.getPhone());
                order.setStatus("PENDING");
                order.setTotalAmount(BigDecimal.valueOf(checkOutRequest.getTotalPrice()));
                order.setVoucherCode("");
                order.setUser(user.get());
                order.setVoucher(null);
                Order orderObject = orderRepository.save(order);

                List<CheckOutProductsResponse> checkOutProductsResponses = checkOutUserService.getCheckOutProductDetailByUserIDs(accountId);
                for (CheckOutProductsResponse checkOutProductsResponse : checkOutProductsResponses) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setPrice(BigDecimal.valueOf(checkOutProductsResponse.getTotalPriceProductDetail()));
                    orderDetail.setProductDetailId((long) checkOutProductsResponse.getProductDetailId());
                    orderDetail.setQuantity(checkOutProductsResponse.getTotalQuantityProductDetail());
                    orderDetail.setOrder(orderObject);
                    orderDetailRepository.save(orderDetail);
                }

                // Delete cart
                if (!cartUserService.getCartTableByUserId(accountId).isEmpty()) {
                    List<CartTableUserResponse> cartTableUserResponses = cartUserService.getCartTableByUserId(accountId);
                    for (CartTableUserResponse cartTableUserResponse : cartTableUserResponses) {
                        cartRepository.deleteById((long) cartTableUserResponse.getCartId());
                    }
                }

                // Insert OrderReview
                OrderReview orderReview = new OrderReview();
                orderReview.setImage("");
                orderReview.setMessage("");
                orderReview.setOrderCode(String.valueOf(orderCode));
                orderReview.setRating(0);
                orderReview.setUpdateTimeForm("0");
                orderReview.setUpdateTimeImage("0");
                orderReview.setUser(user.get());
                orderReviewRepository.save(orderReview);
            } else {
                Voucher voucher = voucherRepository.findById(checkOutRequest.getVoucherId() * 1L).get();
                Order order = new Order();
                order.setAddress(myAddressBook.getAddress());
                order.setDiscountAmount(BigDecimal.valueOf(checkOutRequest.getPercentDiscount()));
                order.setEmail(myAddressBook.getEmail());
                order.setFinalAmount(BigDecimal.valueOf(checkOutRequest.getTotalPrice()));
                order.setFullName(myAddressBook.getFullName());
                order.setNote(myAddressBook.getNote() + " (" + myAddressBook.getSetToAddress() + ")");
                order.setOrderCode(String.valueOf(orderCode));
                order.setOrderDate(LocalDateTime.now());
                order.setPhone(myAddressBook.getPhone());
                order.setStatus("PENDING");
                order.setTotalAmount(BigDecimal.valueOf(checkOutRequest.getSubTotal()));
                order.setVoucherCode(voucher.getVoucherCode());
                order.setUser(user.get());
                order.setVoucher(voucher);
                Order orderObject = orderRepository.save(order);

                List<CheckOutProductsResponse> checkOutProductsResponses = checkOutUserService.getCheckOutProductDetailByUserIDs(accountId);
                for (CheckOutProductsResponse checkOutProductsResponse : checkOutProductsResponses) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setPrice(BigDecimal.valueOf(checkOutProductsResponse.getTotalPriceProductDetail()));
                    orderDetail.setProductDetailId((long) checkOutProductsResponse.getProductDetailId());
                    orderDetail.setQuantity(checkOutProductsResponse.getTotalQuantityProductDetail());
                    orderDetail.setOrder(orderObject);
                    orderDetailRepository.save(orderDetail);
                }

                // Delete cart
                if (!cartUserService.getCartTableByUserId(accountId).isEmpty()) {
                    List<CartTableUserResponse> cartTableUserResponses = cartUserService.getCartTableByUserId(accountId);
                    for (CartTableUserResponse cartTableUserResponse : cartTableUserResponses) {
                        cartRepository.deleteById((long) cartTableUserResponse.getCartId());
                    }
                }

                // Insert number voucher had used of user
                voucher.setUsedQuantity(voucher.getUsedQuantity() + 1);
                voucherRepository.save(voucher);

                // Insert OrderReview
                OrderReview orderReview = new OrderReview();
                orderReview.setImage("");
                orderReview.setMessage("");
                orderReview.setOrderCode(String.valueOf(orderCode));
                orderReview.setRating(0);
                orderReview.setUpdateTimeForm("0");
                orderReview.setUpdateTimeImage("0");
                orderReview.setUser(user.get());
                orderReviewRepository.save(orderReview);
            }

            return new ResponseResult<>("SUCCESS", "Order thành công", true);
        }
    }

    public ResponseResult<CheckOutResponse> checkValidateCheckOut(CheckOutRequest checkOutRequest) {
        if (checkOutRequest == null || (checkOutRequest.getAddressId() == 0 && checkOutRequest.getSubTotal() == 0.0 &&
                checkOutRequest.getVoucherId() == 0 &&
                checkOutRequest.getPercentDiscount() == 0.0 &&
                checkOutRequest.getTotalPrice() == 0.0)) {
            return new ResponseResult<>("ERROR", "Không tìm thấy request", false);
        } else {
            if (checkOutRequest.getAddressId() == 0) {
                return new ResponseResult<>("ERROR", "Hãy thêm thông tin địa chỉ để đặt hàng", false);
            }
        }
        return new ResponseResult<>("SUCCESS", "Order thành công", true);
    }


    //View order
    public List<OrderUserResponse> getListOrders(long accountId) {
        List<Object[]> raw = orderRepository.rawGetOrders(accountId);

        return raw.stream().map(row -> {
            Timestamp timestamp = (Timestamp) row[2];
            return new OrderUserResponse(
                    ((Number) row[0]).intValue(),
                    (String) row[1],
                    timestamp.toLocalDateTime(),
                    (String) row[3],
                    (String) row[4],
                    ((BigDecimal) row[5]).doubleValue(),
                    ((Number) row[6]).intValue()
            );
        }).toList();
    }


    public List<OrderUserResponse> getListOrdersOrEmpty(int userId) {
        if (getListOrders(userId).isEmpty()) {
            return new ArrayList<>();
        } else {
            return getListOrders(userId);
        }
    }

    public ResponseResult<OrderUserResponse> cancelOrder(String orderId, int accountId) {
        Optional<Order> orderOpt = orderRepository.findById(Long.parseLong(orderId));
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus("CANCELLED");
            orderRepository.save(order);


            Optional<User> userOpt = userRepository.findByUserIdNative(accountId);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Optional<Wallets> walletsOpt = walletsRepository.findByUser(user);

                if (walletsOpt.isPresent()) {
                    Wallets wallet = walletsOpt.get();

                    BigDecimal currentBalance = wallet.getBalance();
                    BigDecimal plusAmount = order.getTotalAmount();

                    BigDecimal updatedBalance = currentBalance.add(plusAmount);
                    wallet.setBalance(updatedBalance);

                    walletsRepository.save(wallet);
                } else {
                    System.out.println("Ví không tồn tại cho user ID: " + accountId);
                }
            } else {
                System.out.println("User không tồn tại với ID: " + accountId);
            }
        }

        return new ResponseResult<>("SUCCESS", "", true);
    }
}

