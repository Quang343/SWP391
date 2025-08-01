package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

//@Component
//@ControllerAdvice
//@RequiredArgsConstructor
//public class GlobalNotificationAdvice {
//
//    private final NotificationService notificationService;
//
//    private final UserService userService;
//
//    @ModelAttribute
//    public void addNotificationsToModel(Model model, HttpServletRequest request, HttpSession session) {
//        // Lấy user đang đăng nhập (giả sử bạn dùng Spring Security)
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
//            UserResponse userResponse = (UserResponse) session.getAttribute("account");
//            User currentUser = userService.findById(userResponse.getUserID() * 1L);
//
//            List<Notification> notifications = notificationService.getUserNotifications(currentUser.getUserId());
//
//            model.addAttribute("notifications", notifications);
//            model.addAttribute("unreadCount", notificationService.countUnseenNotifications(currentUser.getUserId()));
//        }
//    }
//}

@ControllerAdvice(basePackages = "com.example.AgriculturalWarehouseManagement.Backend.controllers.admin")
@RequiredArgsConstructor
public class GlobalNotificationAdvice {

    private final NotificationService notificationService;
    private final UserService userService;

    @ModelAttribute
    public void addNotificationsToModel(Model model, HttpServletRequest request, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Chỉ xử lý khi user đã đăng nhập và không phải anonymous
//        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
//
//        }
        UserResponse userResponse = (UserResponse) session.getAttribute("accountAdmin");
        if (userResponse != null) {
            int userId = userResponse.getUserID();
            User currentUser = userService.findById(Long.valueOf(userId));

            List<Notification> notifications = notificationService.findTop10ByReceiverUserIdOrderByCreatedAtDesc(currentUser.getUserId());
            model.addAttribute("notifications", notifications);
            model.addAttribute("unreadCount", notificationService.countUnseenNotifications(currentUser.getUserId()));
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("GlobalNotificationAdvice đã được khởi tạo");
    }

    @ModelAttribute
    public void globalAttributes(Model model, Principal principal) {
        System.out.println("GlobalNotificationAdvice đang được apply cho: " + (principal != null ? principal.getName() : "Guest"));
    }
}