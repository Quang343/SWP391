package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.User_SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class User_SellerServiceImpl implements User_SellerService {
    private final User_SellerRepository userRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public User_SellerDTO getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return mapToDTO(user);
    }

    @Override
    public User_SellerDTO updateUserProfile(User_SellerDTO dto) {
        User user = userRepository.findById((long)dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + dto.getUserId()));

        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());

        if (dto.getDob() != null && !dto.getDob().isEmpty()) {
            LocalDate dob = LocalDate.parse(dto.getDob(), dateFormat);
            LocalDate today = LocalDate.now();
            int age = today.getYear() - dob.getYear();

            if (dob.isAfter(today)) {
                throw new IllegalArgumentException("Ngày sinh không được lớn hơn hiện tại.");
            } else if (age < 10 || age > 100) {
                throw new IllegalArgumentException("Tuổi phải từ 10 đến 100.");
            }

            user.setDob(dob);
        }

        user.setPhone(dto.getPhone());
        user.setUserName(dto.getUsername()); // ✅ CẬP NHẬT USERNAME
        user.setPassword(dto.getPassword());

        userRepository.save(user);
        return mapToDTO(user);
    }

    private User_SellerDTO mapToDTO(User user) {
        return User_SellerDTO.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .dob(user.getDob() != null ? user.getDob().format(dateFormat) : "")
                .createdAt(user.getCreatedAt() != null ? user.getCreatedAt().format(dateFormat) : "")
                .email(user.getEmail())
                .phone(user.getPhone())
                .username(user.getUserName()) // map lại đúng username
                .password(user.getPassword())
                .image(user.getImage()) // 👈 Bổ sung dòng này
                .build();
    }

    @Override
    public Optional<User_SellerDTO> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(this::mapToDTO);
    }


    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + userId));
//        passwordEncoder.matches(loginRequest.getPassword(), passwordDatabase)
        if (!passwordEncoder.matches(currentPassword,user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu hiện tại không đúng.");
        }

        if (user.getPassword().equals(newPassword)) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng mật khẩu hiện tại.");
        }
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Value("${app.upload.product-dir}")
    private String rootUploadDir;

    @Override
    public String saveAvatar(Long userId, MultipartFile file) throws Exception {
        // Kiểm tra định dạng file
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        if (extension.equals("webp")) {
            throw new IllegalArgumentException("Định dạng ảnh không được hỗ trợ (webp). Vui lòng chọn PNG hoặc JPG.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        // Đọc ảnh gốc
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        if (originalImage == null) {
            throw new IllegalArgumentException("File không phải là ảnh hợp lệ.");
        }

        // Cắt về hình vuông (center crop)
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int squareSize = Math.min(width, height);
        int x = (width - squareSize) / 2;
        int y = (height - squareSize) / 2;
        BufferedImage cropped = originalImage.getSubimage(x, y, squareSize, squareSize);

        // Tạo đường dẫn thư mục upload
        Path uploadDir = Paths.get(rootUploadDir, "Seller", String.valueOf(userId));
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Tên cố định
        String newFileName = "avatar." + extension;
        Path outputPath = uploadDir.resolve(newFileName);
        ImageIO.write(cropped, extension, outputPath.toFile());

        // Cập nhật tên vào DB
        user.setImage(newFileName);
        userRepository.save(user);

        return "/seller/" + userId + "/" + newFileName;
    }



}
