package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CheckOutRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.SellerApplicationResponseDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.SellerApplicationRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.WalletsUsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerApplicationService {

    private final SellerApplicationRepository sellerApplicationRepository;
    private final UserRepository userRepository;
    private final WalletsUsersService walletsUsersService;

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    public SellerApplication createApplication(Long userId, int contractMonths, MultipartFile cvFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (sellerApplicationRepository.existsByUserAndStatus(user, SellerApplicationStatus.PENDING)) {
            throw new RuntimeException("Bạn đã có một đơn đăng ký đang chờ duyệt");
        }
        if (sellerApplicationRepository.existsByUserAndStatus(user, SellerApplicationStatus.APPROVED)) {
            throw new RuntimeException("Đơn đăng ký trước của bạn đã được duyệt. Không thể thực hiện lại yêu cầu này");
        }

        // --- Trừ tiền trước khi tạo đơn ---
        BigDecimal basePricePerMonth = new BigDecimal("100000");
        BigDecimal discountRate = BigDecimal.ZERO;

        if (contractMonths >= 12) {
            discountRate = new BigDecimal("0.25"); // 25%
        } else if (contractMonths >= 6) {
            discountRate = new BigDecimal("0.15"); // 15%
        } else if (contractMonths >= 3) {
            discountRate = new BigDecimal("0.10"); // 10%
        }

        BigDecimal originalTotal = basePricePerMonth.multiply(BigDecimal.valueOf(contractMonths));
        BigDecimal discountedAmount = originalTotal.multiply(discountRate);
        BigDecimal finalTotal = originalTotal.subtract(discountedAmount);

        // Nếu muốn cộng thêm phí đăng ký cố định (ví dụ 50k):
        CheckOutRequest checkout = new CheckOutRequest();
        checkout.setTotalPrice(finalTotal.doubleValue());

        // Trừ tiền
        walletsUsersService.updateWalletAfterOrder(userId.intValue(), checkout);
        // --- End trừ tiền ---

        // Lưu file CV
        String filename;
        try {
            filename = storeFile(cvFile, userId);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file CV: " + e.getMessage());
        }

        // Tạo đơn đăng ký
        SellerApplication app = new SellerApplication();
        app.setUser(user);
        app.setContractMonths(contractMonths);
        app.setCvImage(filename);
        app.setCreatedAt(LocalDateTime.now());
        app.setStatus(SellerApplicationStatus.PENDING);

        return sellerApplicationRepository.save(app);
    }

    public boolean existsPendingApplication(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return sellerApplicationRepository.existsByUserAndStatus(user, SellerApplicationStatus.PENDING);
    }

    public boolean existsApprovedApplication(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return sellerApplicationRepository.existsByUserAndStatus(user, SellerApplicationStatus.APPROVED);
    }


    private String storeFile(MultipartFile file, Long userId) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("Tên tệp trống");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex);
        }

        // Luôn lưu tên là "cv" + extension (ví dụ: "cv.pdf")
        String fixedFileName = "cv" + extension;

        Path userFolder = Paths.get(uploadDir + "/Seller/" + userId);
        if (!Files.exists(userFolder)) {
            Files.createDirectories(userFolder);
        }

        Path destination = userFolder.resolve(fixedFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return fixedFileName;
    }

    public List<SellerApplicationResponseDTO> getApplicationsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        List<SellerApplication> applications = sellerApplicationRepository.findByUser(user);

        return applications.stream().map(app -> {
            LocalDate approvalDate = null;
            LocalDate cancelDeadline = null;

            if (app.getContractExpiryDate() != null && app.getContractMonths() != null) {
                approvalDate = app.getContractExpiryDate().minusMonths(app.getContractMonths());
                cancelDeadline = approvalDate.plusDays(7);
            }

            return SellerApplicationResponseDTO.builder()
                    .id(app.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .contractMonths(app.getContractMonths())
                    .cvImage(app.getCvImage())
                    .status(app.getStatus())
                    .createdAt(app.getCreatedAt())
                    .contractExpiryDate(app.getContractExpiryDate())
                    .cancelDeadline(approvalDate)          // thêm trường này vào DTO nếu cần
                    .cancelDeadline(cancelDeadline)      // hoặc truyền deadline về frontend
                    .build();
        }).toList();

    }

    @Transactional
    public void cancelPendingApplication(Long userId, Long applicationId) {
        System.out.println("Canceling application ID: " + applicationId + " for user ID: " + userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + userId));

        SellerApplication app = (SellerApplication) sellerApplicationRepository
                .findByIdAndUserAndStatus(applicationId, user, SellerApplicationStatus.PENDING)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn đăng ký đang chờ duyệt với ID: " + applicationId));

        try {
            int contractMonths = app.getContractMonths();
            System.out.println("Contract months: " + contractMonths);
            BigDecimal basePricePerMonth = new BigDecimal("100000.00");
            BigDecimal discountRate = contractMonths >= 12 ? new BigDecimal("0.25") :
                    contractMonths >= 6 ? new BigDecimal("0.15") :
                            contractMonths >= 3 ? new BigDecimal("0.10") : BigDecimal.ZERO;

            BigDecimal originalTotal = basePricePerMonth.multiply(new BigDecimal(contractMonths));
            BigDecimal discountedAmount = originalTotal.multiply(discountRate);
            BigDecimal finalTotal = originalTotal.subtract(discountedAmount);
            BigDecimal refundAmount = finalTotal.multiply(new BigDecimal("0.5"));

            System.out.println("Refund amount: " + refundAmount);
            CheckOutRequest refund = new CheckOutRequest();
            refund.setTotalPrice(-refundAmount.doubleValue());
            walletsUsersService.updateWalletAfterOrder(userId.intValue(), refund);
            System.out.println("Refund processed for user ID: " + userId);

            app.setStatus(SellerApplicationStatus.CANCELLED);
            sellerApplicationRepository.save(app);
            System.out.println("Application ID: " + applicationId + " status updated to CANCELLED");
        } catch (Exception e) {
            System.err.println("Error during cancellation: " + e.getMessage());
            throw new RuntimeException("Lỗi khi xử lý hủy đơn: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void restorePendingApplication(Long userId, Long applicationId) {
        System.out.println("Restoring application ID: " + applicationId + " for user ID: " + userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + userId));

        SellerApplication app = sellerApplicationRepository
                .findByIdAndUserAndStatusIn(applicationId, user,
                        Arrays.asList(SellerApplicationStatus.CANCELLED, SellerApplicationStatus.EXPIRED))
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn đăng ký với ID: " + applicationId + " ở trạng thái CANCELLED hoặc EXPIRED"));

        try {
            int contractMonths = app.getContractMonths();
            System.out.println("Contract months: " + contractMonths);
            BigDecimal basePricePerMonth = new BigDecimal("100000.00");
            BigDecimal discountRate = contractMonths >= 12 ? new BigDecimal("0.25") :
                    contractMonths >= 6 ? new BigDecimal("0.15") :
                            contractMonths >= 3 ? new BigDecimal("0.10") : BigDecimal.ZERO;

            BigDecimal originalTotal = basePricePerMonth.multiply(new BigDecimal(contractMonths));
            BigDecimal discountedAmount = originalTotal.multiply(discountRate);
            BigDecimal finalTotal = originalTotal.subtract(discountedAmount).setScale(2, RoundingMode.HALF_UP);

            System.out.println("Payment amount: " + finalTotal);
            CheckOutRequest payment = new CheckOutRequest();
            payment.setTotalPrice(finalTotal.doubleValue()); // Full contract amount
            walletsUsersService.updateWalletAfterOrder(userId.intValue(), payment);
            System.out.println("Payment processed for user ID: " + userId);

            app.setStatus(SellerApplicationStatus.PENDING);
            app.setCreatedAt(LocalDateTime.now());
            sellerApplicationRepository.save(app);
            System.out.println("Application ID: " + applicationId + " restored to PENDING, createdAt updated to: " + app.getCreatedAt());
        } catch (Exception e) {
            System.err.println("Error during restoration: " + e.getMessage());
            throw new RuntimeException("Lỗi khi xử lý khôi phục đơn: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void autoCancelApplications() {
        LocalDate today = LocalDate.now();
        List<SellerApplication> applications = sellerApplicationRepository.findAllApprovedApplications();

        for (SellerApplication app : applications) {
            LocalDate expiryDate = app.getContractExpiryDate();
            Integer contractMonths = app.getContractMonths();

            if (expiryDate == null || contractMonths == null) continue;

            // Ngày duyệt = ngày hết hạn - số tháng hợp đồng
            LocalDate approvalDate = expiryDate.minusMonths(contractMonths);

            // Nếu đã quá 7 ngày từ khi duyệt mà user vẫn chưa hoàn tất, thì huỷ đơn
            LocalDate cancelDeadline = approvalDate.plusDays(7);

            if (today.isAfter(cancelDeadline)) {
                Long appId = app.getId();
                Long userId = app.getUser().getUserId().longValue();
                User user = app.getUser();

                try {
                    BigDecimal basePricePerMonth = new BigDecimal("100000.00");
                    BigDecimal discountRate = contractMonths >= 12 ? new BigDecimal("0.25") :
                            contractMonths >= 6 ? new BigDecimal("0.15") :
                                    contractMonths >= 3 ? new BigDecimal("0.10") : BigDecimal.ZERO;

                    BigDecimal originalTotal = basePricePerMonth.multiply(new BigDecimal(contractMonths));
                    BigDecimal discountedAmount = originalTotal.multiply(discountRate);
                    BigDecimal refundAmount = originalTotal.subtract(discountedAmount);

                    System.out.println("==> Đang hoàn lại " + refundAmount + " cho user ID: " + userId);

                    CheckOutRequest refund = new CheckOutRequest();
                    refund.setTotalPrice(-refundAmount.doubleValue());
                    walletsUsersService.updateWalletAfterOrder(userId.intValue(), refund);
                    System.out.println("==> Hoàn tiền thành công cho user ID: " + userId);

                    app.setStatus(SellerApplicationStatus.CANCELLED);
                    app.setContractExpiryDate(null);
                    sellerApplicationRepository.save(app);
                    System.out.println("==> Đã huỷ hồ sơ #" + appId + " và cập nhật DB");
                } catch (Exception e) {
                    System.err.println("Lỗi hoàn tiền/hủy hồ sơ #" + appId + ": " + e.getMessage());
                }
            }
        }
    }

    public void updateCv(Long applicationId, MultipartFile file) throws Exception {
        SellerApplication app = sellerApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn đăng ký"));

        Long userId = app.getUser().getUserId().longValue();

        // Lưu file và lấy tên cố định như "cv.jpg"
        String fileName = storeFile(file, userId); // fileName sẽ là "cv.jpg", "cv.pdf", ...

        // Chỉ lưu tên file vào DB
        app.setCvImage(fileName);

        sellerApplicationRepository.save(app);
    }

    public Optional<SellerApplication> findByUserAndStatus(User user, SellerApplicationStatus status) {
        return sellerApplicationRepository.findByUserAndStatus(user, status);
    }

    ///
    public Optional<SellerApplication> findSellerApplicationById(Long id){
        return sellerApplicationRepository.findById(id);
    }

    public SellerApplication save(SellerApplication application) {
        return sellerApplicationRepository.save(application);
    }

}

