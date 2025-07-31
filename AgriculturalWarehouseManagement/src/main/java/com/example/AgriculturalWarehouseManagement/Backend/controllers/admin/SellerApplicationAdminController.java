package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.MailBody;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.SellerApplicationRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.EmailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.NotificationService;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.SellerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SellerApplicationAdminController {

    private final NotificationService notificationService;

    private final SellerApplicationService sellerApplicationService;

    private final EmailService emailService;

    private final SellerApplicationRepository sellerApplicationRepository;

    @GetMapping("/admin/seller-applications/{saId}/details")
    public String viewSellerApplication(@PathVariable("saId") Long saId, Model model){
        SellerApplication sellerApplication = sellerApplicationService
                .findSellerApplicationById(saId).orElseThrow(() -> new RuntimeException("Seller application not found"));
        User user = sellerApplication.getUser();
        model.addAttribute("user", user);
        model.addAttribute("sa", sellerApplication);
        return "BackEnd/Admin/Seller_Application";
    }

    @PostMapping("/admin/seller-applications/{id}/approve")
    public String approve(@PathVariable("id") Long saId, RedirectAttributes redirectAttributes) {
        Optional<SellerApplication> optionalSa = sellerApplicationService.findSellerApplicationById(saId);

        if (optionalSa.isPresent()) {
            SellerApplication sa = optionalSa.get();
            if(sa.getStatus() == SellerApplicationStatus.PENDING){
                sa.setStatus(SellerApplicationStatus.APPROVED);
                sa.setContractExpiryDate(LocalDate.now().plusMonths(sa.getContractMonths()));
                sellerApplicationService.save(sa);
                redirectAttributes.addFlashAttribute("successMessage", "Seller application approved successfully.");

                MailBody mailBody = MailBody.builder()
                        .to(sa.getUser().getEmail())
                        .subject("Yêu cầu đăng ký bán hàng của bạn đã được phê duyệt")
                        .text("Xin chào " + sa.getUser().getFullName() + ",\n\n"
                                + "Chúng tôi rất vui thông báo rằng hồ sơ đăng ký trở thành người bán của bạn đã được phê duyệt.\n"
                                + "Bạn đã có thể bắt đầu đăng và bán sản phẩm trên hệ thống.\n\n"
                                + "Ngày hết hạn hợp đồng: " + sa.getContractExpiryDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n"
                                + "Vui lòng xác nhận sau đó đăng nhập vào hệ thống để truy cập trang quản lý của người bán.\n\n"
                                + "Trân trọng,\n"
                                + "Đội ngũ AgriWarehouse")
                        .build();
                emailService.sendSimpleMessage(mailBody);
            }else{
                redirectAttributes.addFlashAttribute("errorMessage", "Only pending applications can be approved.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Seller application not found.");
        }

        return "redirect:/admin";
    }




    @PostMapping("/admin/seller-applications/{id}/reject")
    public String reject(@PathVariable("id") Long saId, RedirectAttributes redirectAttributes) {
        Optional<SellerApplication> optionalSa = sellerApplicationService.findSellerApplicationById(saId);

        if (optionalSa.isPresent()) {
            SellerApplication sa = optionalSa.get();
            if (sa.getStatus() == SellerApplicationStatus.PENDING) {
                sa.setStatus(SellerApplicationStatus.REJECTED);
                sellerApplicationService.save(sa);
                redirectAttributes.addFlashAttribute("successMessage", "Seller application has been rejected!");
                MailBody mailBody = MailBody.builder()
                        .to(sa.getUser().getEmail())
                        .subject("Hồ sơ đăng ký bán hàng của bạn đã bị từ chối")
                        .text("Xin chào " + sa.getUser().getFullName() + ",\n\n"
                                + "Cảm ơn bạn đã quan tâm và gửi hồ sơ đăng ký trở thành người bán trên hệ thống của chúng tôi.\n"
                                + "Rất tiếc, sau khi xem xét, hồ sơ của bạn chưa đáp ứng yêu cầu và đã bị từ chối.\n\n"
                                + "Nếu bạn cho rằng đây là sự nhầm lẫn hoặc muốn đăng ký lại, vui lòng kiểm tra và cập nhật lại thông tin hồ sơ.\n\n"
                                + "Trân trọng,\n"
                                + "Đội ngũ AgriWarehouse")
                        .build();

                emailService.sendSimpleMessage(mailBody);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Only pending applications can be rejected.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Seller application not found.");
        }

        return "redirect:/admin";
    }

}
