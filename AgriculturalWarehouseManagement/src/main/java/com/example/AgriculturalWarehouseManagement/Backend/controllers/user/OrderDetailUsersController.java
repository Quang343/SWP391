package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderDetailUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.OrderReviewUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.OrderReview;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderDetailUserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.OrderReviewUserService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//        maxFileSize = 1024 * 1024 * 10, // 10MB
//        maxRequestSize = 1024 * 1024 * 50 // 50MB
//)
public class OrderDetailUsersController {

    @Autowired
    private OrderDetailUserService orderDetailUserService;

    @Autowired
    private OrderReviewUserService orderReviewUserService;

    @GetMapping("/orderDetailsUser")
    public String orderDetailsUser(@RequestParam(name = "orderCode", defaultValue = "0") String orderCode,
                                   Model model, HttpSession session) {
        ResponseResult<List<OrderDetailUserResponse>> orderDetailUserResponses = orderDetailUserService.getListOrderDetailsUserAndEmpty(orderCode);
        if (!orderDetailUserResponses.isActive()) {
            model.addAttribute("errorMessageOrderDetail", orderDetailUserResponses.getMessage());
            return "FrontEnd/Home/orderTracking";
        }

        // View order
        OrderDetailUserResponse rightNavOrderDetailUserResponses = orderDetailUserResponses.getData().get(0);
        model.addAttribute("orderDetailUserResponses", orderDetailUserResponses.getData());
        model.addAttribute("rightNavOrderDetailUserResponses", rightNavOrderDetailUserResponses);

        // View orderReview
        ResponseResult<OrderReviewUserResponse> result = orderReviewUserService.getOrderReviewObject(orderCode);
        if (!result.isActive()) {
            model.addAttribute("messageOrderReview", result.getMessage());
            model.addAttribute("activeOrderReview", result.isActive());
            model.addAttribute("orderReviewUserResponse", result.getData());
        } else {
            model.addAttribute("messageOrderReview", result.getMessage());
            model.addAttribute("activeOrderReview", result.isActive());
            model.addAttribute("orderReviewUserResponse", result.getData());
        }

        ResponseResult<OrderReviewUserResponse> resultImage = orderReviewUserService.getOrderReviewObjectImage(orderCode);
        if (!resultImage.isActive()) {
            System.out.println("OrderReviewUserResponse: " + resultImage.isActive());
            model.addAttribute("activeOrderReviewImage", resultImage.isActive());
            model.addAttribute("orderReviewUserResponseImage", resultImage.getData());
        } else {
            model.addAttribute("activeOrderReviewImage", resultImage.isActive());
            model.addAttribute("orderReviewUserResponseImage", resultImage.getData());
        }

        // View Update form
        Object updateForm = session.getAttribute("messageUpdateForm");
        if (updateForm != null) {
            model.addAttribute("messageUpdateForm", updateForm.toString());
            session.removeAttribute("messageUpdateForm");
        }

        // View Update image
        Object updateImage = session.getAttribute("messageUpdateImage");
        if (updateImage != null) {
            model.addAttribute("messageUpdateImage", updateImage.toString());
            session.removeAttribute("messageUpdateImage");
        }

        return "FrontEnd/Home/orderDetail";
    }

    @PostMapping("/orderDetailsUser")
    public String orderDetailsUser(@RequestParam(name = "orderReviewId",  defaultValue = "0") int orderReviewId,
                                   @RequestParam(name = "orderCode",  defaultValue = "0") String orderCode,
                                   @RequestParam(name = "rating", defaultValue = "0") int rating,
                                   @RequestParam(name = "message", defaultValue = "") String message,
                                   HttpSession session) {
        System.out.println("hello"+message);
        ResponseResult<OrderReview> result = orderReviewUserService.editOrderReviewUserForm(orderReviewId, rating, message);
        if (result.isActive()) {
            session.setAttribute("messageUpdateForm", result.getMessage());
            return "redirect:/orderDetailsUser?orderCode=" + orderCode;
        } else {
            session.setAttribute("messageUpdateForm", result.getMessage());
            return "redirect:/orderDetailsUser?orderCode=" + orderCode;
        }
    }

    @PostMapping("/editOrderReview/image")
    public String editOrderReviewImage(@RequestParam("image") MultipartFile file,
                                       @RequestParam(name = "orderReviewId",  defaultValue = "0") int orderReviewId,
                                       @RequestParam(name = "orderCode",  defaultValue = "0") String orderCode,
                                       @Value("${app.upload.user-dir}") String uploadDir,
                                       HttpSession session) throws IOException {

        // Giữ ảnh cũ nếu không có ảnh mới
        String imagePath = ""; // hoặc lấy từ DB nếu bạn muốn cập nhật

        if (file != null && !file.isEmpty()) {
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" +
                    StringUtils.cleanPath(file.getOriginalFilename());

            Path filePath = Paths.get(uploadDir, fileName);
            file.transferTo(filePath.toFile());

            // Tạo đường dẫn public (dùng trong <img>)
            imagePath = "/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/" + fileName;

        }

        ResponseResult<OrderReview> result = orderReviewUserService.editOrderReviewUserImage(orderReviewId, imagePath, file);
        if (result.isActive()) {
            session.setAttribute("messageUpdateImage", result.getMessage());
            return "redirect:/orderDetailsUser?orderCode=" + orderCode;
        } else {
            session.setAttribute("messageUpdateImage", result.getMessage());
            return "redirect:/orderDetailsUser?orderCode=" + orderCode;
        }
    }

}
