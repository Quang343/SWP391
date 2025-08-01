package com.example.AgriculturalWarehouseManagement.Backend.controllers.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.SellerApplicationRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.NotificationRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.RoleRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.SellerApplicationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/applications")
@RequiredArgsConstructor
public class SellerApplicationController {

    private final SellerApplicationService sellerApplicationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    NotificationService notificationService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> submitApplication(
            @RequestParam("contractMonths") int contractMonths,
            @RequestParam("cvFile") MultipartFile cvFile,
            HttpSession session) {

        Integer idInt = (Integer) session.getAttribute("accountId");
        if (idInt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        Long userId = idInt.longValue();

        try {
            SellerApplication created = sellerApplicationService.createApplication(userId, contractMonths, cvFile);

            // 2. Tạo Notification
            // Sau khi user gửi đơn trở thành seller
            Optional<User> requestingUser = userRepository.findByUserId(userId.intValue()); // user gửi yêu cầu
            User adminUser = userRepository.findByRole(roleRepository.findByRoleName("ADMIN")).get(0); // lấy admin đầu tiên

            if (requestingUser.isPresent() && adminUser != null) {
                Notification notification = getNotification(requestingUser, adminUser);
                notification.setUrl("/admin/seller-applications/" + created.getId() + "/details");
                notificationRepository.save(notification);

                // 3. Gửi WebSocket message cho admin
                notificationService.sendNotificationToAdmin(notification);
            }
            //

            return ResponseEntity.ok(Map.of(
                    "message", "Gửi đơn thành công",
                    "applicationId", created.getId()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Lỗi hệ thống: " + e.getMessage()));
        }
    }

    private static Notification getNotification(Optional<User> requestingUser, User adminUser) {
        Notification notification = new Notification();
        notification.setTitle("Yêu cầu trở thành người bán");
        notification.setContent("Người dùng " + requestingUser.get().getFullName() + " đã gửi yêu cầu trở thành người bán.");
        notification.setStatus("UNSEEN"); // hoặc "UNREAD"
//                notification.setUrl("/admin/seller-applications/" + sellerApplication.getId() + "/details");
        notification.setReceiver(adminUser);
        return notification;
    }

    @GetMapping("/check-status")
    public ResponseEntity<Map<String, Boolean>> checkAllStatus(@RequestParam Long userId) {
        boolean hasPending = sellerApplicationService.existsPendingApplication(userId);
        boolean isApproved = sellerApplicationService.existsApprovedApplication(userId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("hasPending", hasPending);
        response.put("isApproved", isApproved);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/my")
    public ResponseEntity<?> getMyApplications(HttpSession session) {
        Integer idInt = (Integer) session.getAttribute("accountId");
        if (idInt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        Long userId = idInt.longValue();
        return ResponseEntity.ok(sellerApplicationService.getApplicationsByUserId(userId));
    }

    @PutMapping("/{applicationId}/cancel")
    public ResponseEntity<?> cancelPendingApplication(@PathVariable Long applicationId, HttpSession session) {
        Integer accountId = (Integer) session.getAttribute("accountId");
        System.out.println("Session accountId: " + accountId); // Debug
        if (accountId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        try {
            sellerApplicationService.cancelPendingApplication(accountId.longValue(), applicationId);
            return ResponseEntity.ok(Map.of("message", "Hủy đơn thành công. 50% số tiền đã được hoàn lại vào ví của bạn."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Lỗi hệ thống: " + e.getMessage()));
        }
    }

    @PutMapping("/{applicationId}/restore")
    public ResponseEntity<?> restoreCancelledOrExpiredApplication(@PathVariable Long applicationId, HttpSession session) {
        Integer accountId = (Integer) session.getAttribute("accountId");
        System.out.println("Session accountId: " + accountId); // Debug

        if (accountId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        try {
            sellerApplicationService.restorePendingApplication(accountId.longValue(), applicationId);
            return ResponseEntity.ok(Map.of("message", "Khôi phục đơn thành công. Hệ thống đã trừ tiền trong ví của bạn."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Lỗi hệ thống: " + e.getMessage()));
        }
    }

    @GetMapping("/test-auto-cancel")
    public ResponseEntity<?> testAutoCancelNow() {
        sellerApplicationService.autoCancelApplications();
        return ResponseEntity.ok("Đã chạy scheduler thủ công");
    }

    @PutMapping("/{applicationId}/cv")
    public ResponseEntity<?> updateCv(@PathVariable Long applicationId,
                                      @RequestParam("cvFile") MultipartFile file) {
        try {
            sellerApplicationService.updateCv(applicationId, file); // gọi service
            return ResponseEntity.ok("CV đã được cập nhật");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

}
