package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.seller.User_SellerDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller.User_SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class User_SellerServiceImpl implements User_SellerService {
    private final User_SellerRepository userRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public User_SellerDTO getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return mapToDTO(user);
    }

    @Override
    public User_SellerDTO updateUserProfile(User_SellerDTO dto) {
        User user = userRepository.findById(dto.getUserId())
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
                .build();
    }

    @Override
    public Optional<User_SellerDTO> getUserById(int userId) {
        return userRepository.findById(userId)
                .map(this::mapToDTO);
    }


    @Override
    public void changePassword(int userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + userId));

        if (!user.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Mật khẩu hiện tại không đúng.");
        }

        if (user.getPassword().equals(newPassword)) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng mật khẩu hiện tại.");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }


}
