package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.findTop10ByReceiverUserIdOrderByCreatedAtDesc(userId));
//        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PutMapping("/seen/{id}")
    public ResponseEntity<Void> markAsSeen(@PathVariable Long id) {
        notificationService.markAsSeen(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/count/unseen/{userId}")
    public ResponseEntity<Long> countUnseen(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.countUnseenNotifications(userId));
    }

    @GetMapping("/view/{id}")
    public RedirectView viewNotification(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);

        if (notification == null) {
            RedirectView redirectView = new RedirectView("admin");
            redirectView.setContextRelative(true);
            return redirectView;
        }

        notificationService.markAsSeen(id);

        // Lấy URL đích từ notification, đảm bảo bắt đầu bằng dấu /
        RedirectView redirectView = new RedirectView(notification.getUrl());
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @PostMapping("/markAllRead")
    public ResponseEntity<?> markAllAsRead(HttpSession session) {
        UserResponse userResponse = (UserResponse) session.getAttribute("accountAdmin");
        User currentUser = userService.findById(Long.valueOf(userResponse.getUserID())); // Lấy từ SecurityContext
        notificationService.markAllAsReadByUserId(currentUser.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/archive/{id}")
    @ResponseBody
    public ResponseEntity<?> archiveNotification(@PathVariable Long id) {
        try {
            notificationService.achieveNotification(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to archive notification");
        }
    }
}
