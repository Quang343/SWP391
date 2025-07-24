package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderReviewUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderReview;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderReviewRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OrderReviewUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderReviewRepository orderReviewRepository;


    @Autowired
    private jakarta.servlet.http.HttpSession session;


    public List<OrderReviewUserResponse> getListOrders(String orderCode) {
        List<Object[]> raw = orderReviewRepository.rawGetOrderReviews(orderCode);
        return raw.stream().map(row -> {
            Object objTimestampOne = row[5];
            Object objTimestampTwo = row[6];

            LocalDateTime timestampOne = null;
            LocalDateTime timestampTwo = null;

            if (objTimestampOne instanceof Timestamp) {
                timestampOne = ((Timestamp) objTimestampOne).toLocalDateTime();
            }

            if (objTimestampTwo instanceof Timestamp) {
                timestampTwo = ((Timestamp) objTimestampTwo).toLocalDateTime();
            }

            return new OrderReviewUserResponse(
                    ((Number) row[0]).intValue(),
                    (String) row[1],
                    (String) row[2],
                    ((Number) row[3]).intValue(),
                    (String) row[4],
                    timestampOne,
                    timestampTwo,
                    (String) row[7],
                    (String) row[8],
                    ((Number) row[9]).intValue(),
                    (String) row[10],
                    (String) row[11]
            );
        }).toList();
    }


    public ResponseResult<OrderReviewUserResponse> getOrderReviewObject(String orderCode) {
        if (getListOrders(orderCode).isEmpty()) {
            return new ResponseResult<>("ERROR", "Không có Order Code", false, new OrderReviewUserResponse());
        } else {
            OrderReviewUserResponse orderReviewUserResponse = getListOrders(orderCode).get(0);
            LocalDateTime orderDate = orderReviewUserResponse.getOrderDate();
            LocalDateTime now = LocalDateTime.now();

            Object userEntity = session.getAttribute("account");

            if (userEntity == null) {
                return new ResponseResult<>("ERROR", "Không thể review vào sản phẩm của người khác", false, orderReviewUserResponse);
            } else {
                UserResponse userResponse = (UserResponse) userEntity;
                if (orderReviewUserResponse.getUserId() != userResponse.getUserID()) {
                    return new ResponseResult<>("ERROR", "Không thể review vào sản phẩm của người khác", false, orderReviewUserResponse);
                }
            }

            if (ChronoUnit.DAYS.between(orderDate, now) > 30) {
                return new ResponseResult<>("ERROR", "Đơn hàng đã quá 30 ngày không thể review", false, orderReviewUserResponse);
            }

            if (orderReviewUserResponse.getUpdateTimeFormatted().equalsIgnoreCase("1")) {
                return new ResponseResult<>("ERROR", "Chỉ được review thông tin sản phẩm một lần duy nhất", false, orderReviewUserResponse);
            }

            return new ResponseResult<>("SUCCESS", "Hãy review thông tin sản phẩm sau khi nhận hàng", true, orderReviewUserResponse);

        }
    }

    public ResponseResult<OrderReviewUserResponse> getOrderReviewObjectImage(String orderCode) {
        if (getListOrders(orderCode).isEmpty()) {
            return new ResponseResult<>("ERROR", "Không có Order Code", false, new OrderReviewUserResponse());
        } else {
            OrderReviewUserResponse orderReviewUserResponse = getListOrders(orderCode).get(0);
            LocalDateTime orderDate = orderReviewUserResponse.getOrderDate();
            LocalDateTime now = LocalDateTime.now();

            Object userEntity = session.getAttribute("account");

            if (userEntity == null) {
                return new ResponseResult<>("ERROR", "Không thể review vào sản phẩm của người khác", false, orderReviewUserResponse);
            } else {
                UserResponse userResponse = (UserResponse) userEntity;
                if (orderReviewUserResponse.getUserId() != userResponse.getUserID()) {
                    return new ResponseResult<>("ERROR", "Không thể review vào sản phẩm của người khác", false, orderReviewUserResponse);
                }
            }

            if (ChronoUnit.DAYS.between(orderDate, now) > 30) {
                return new ResponseResult<>("ERROR", "Đơn hàng đã quá 30 ngày không thể review", false, orderReviewUserResponse);
            }

            if (orderReviewUserResponse.getUpdatedTimeImage().equalsIgnoreCase("1")) {
                return new ResponseResult<>("ERROR", "Chỉ được review thông tin ảnh một lần duy nhất", false, orderReviewUserResponse);
            }

            return new ResponseResult<>("SUCCESS", "Hãy gửi thông tin ảnh sản phẩm ảnh sau khi nhận hàng", true, orderReviewUserResponse);


        }
    }

    public ResponseResult<OrderReview> editOrderReviewUserImage(int orderReviewId, String imagePath, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            Optional<OrderReview> optionalReview = orderReviewRepository.findById((long) orderReviewId);

            if (optionalReview.isPresent()) {
                OrderReview orderReview = optionalReview.get();
                orderReview.setImage(imagePath);
                orderReview.setUpdateTimeImage("1");
                orderReviewRepository.save(orderReview);
                return new ResponseResult<>("SUCCESS", "Sửa ảnh thành công !!!", true);
            } else {
                return new ResponseResult<>("ERROR", "Order review không tìm thấy !!!", false);
            }
        } else {
            return new ResponseResult<>("ERROR", "Hãy gửi đúng ảnh để review sản phẩm!!!", false);
        }
    }

    public ResponseResult<OrderReview> editOrderReviewUserForm(int orderReviewId, int rating, String message) {
        Optional<OrderReview> optionalReview = orderReviewRepository.findById((long) orderReviewId);

        if (optionalReview.isPresent()) {
            OrderReview orderReview = optionalReview.get();
            orderReview.setRating(rating);
            orderReview.setMessage(message);
            orderReview.setUpdateDateTime(LocalDateTime.now());
            orderReview.setUpdateTimeForm("1");
            orderReviewRepository.save(orderReview);
            return new ResponseResult<>("SUCCESS", "Review thông tin thành công !!!", true);
        } else {
            return new ResponseResult<>("ERROR", "Order review không tìm thấy !!!", false);
        }
    }
}
