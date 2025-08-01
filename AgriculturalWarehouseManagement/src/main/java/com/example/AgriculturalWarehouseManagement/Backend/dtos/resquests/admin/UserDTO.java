package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(message = "Role is required")
    private Long roleId;

    private int userId;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must be at most 100 characters")
    private String username;

//    @NotBlank(message = "Full name is required")
    @Size(max = 255)
    private String fullName;

    private MultipartFile image;

    private String imageName;

    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String address;

    private String gender;

    @NotBlank(message = "Status is required")
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

}
