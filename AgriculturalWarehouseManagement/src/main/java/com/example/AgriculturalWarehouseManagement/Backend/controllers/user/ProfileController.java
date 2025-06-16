package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.ProfileRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProfileResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

@Controller
//@RestController
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @GetMapping("profileUser")
    public String getProfileUser(Model model) {

        String token = (String) session.getAttribute("auth_token");

        if (token == null) {
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userService.loadUserByEmail(email);

        if (userEntity == null) {
            return "redirect:/login";
        } else {

            if (session.getAttribute("errorUpdateProfile") != null) {
                String errorUpdateImage = (String) session.getAttribute("errorUpdateProfile");
                model.addAttribute("errorUpdateProfile", errorUpdateImage);
                session.removeAttribute("errorUpdateProfile");
            }

            if (session.getAttribute("errorUpdateImage") != null) {
                String errorUpdateImage = (String) session.getAttribute("errorUpdateImage");
                model.addAttribute("errorUpdateImage", errorUpdateImage);
                session.removeAttribute("errorUpdateImage");
            }

            if (session.getAttribute("errorChangePassword") != null) {
                String errorChangePassword = (String) session.getAttribute("errorChangePassword");
//                System.out.println("hello1"+errorChangePassword);
                model.addAttribute("errorChangePassword", errorChangePassword);
                session.removeAttribute("errorChangePassword");
            }

            if (session.getAttribute("successChangePassword") != null) {
                String successChangePassword= (String) session.getAttribute("successChangePassword");
//                System.out.println("hello1"+successChangePassword);
                model.addAttribute("successChangePassword", successChangePassword);
                session.removeAttribute("successChangePassword");
            }

            return "FrontEnd/Home/userDashboard";

        }

    }

    @PostMapping("/profileUserEdit")
    public String editProfileUser(@ModelAttribute ProfileRequest profileRequest) {

        System.out.println(profileRequest.toString());

        ResponseResult<ProfileResponse> result = userService.editProfileUser(profileRequest);

        if (result.isActive()) {
            User userEntity = userService.loadUserByEmail(profileRequest.getEmail());
            UserResponse userResponse = userService.getUser(userEntity);
            session.setAttribute("account", userResponse);
        } else {
            session.setAttribute("errorUpdateProfile", result.getMessage());
        }


        return "redirect:/profileUser";
    }

    @GetMapping("/profileUserEdit/image")
    public String editProfileUserImage() {
        return "FrontEnd/Home/userDashboard";
    }

    @PostMapping("/profileUserEdit/image")
    public String editProfileUserImage(@RequestParam("image") MultipartFile file,
                                       @Value("${app.upload.user-dir}") String uploadDir,
                                       HttpSession session) throws IOException {
        UserResponse userResponseSession = (UserResponse) session.getAttribute("account");

        String imagePath = userResponseSession.getImageUrl(); // giữ ảnh cũ

        if (file != null && !file.isEmpty()) {
            Files.createDirectories(Paths.get(uploadDir)); // tạo thư mục nếu chưa có -> C:\Users/ADMIN/uploads/user,

            String fileName = System.currentTimeMillis() + "_" +
                    StringUtils.cleanPath(file.getOriginalFilename());

            // Đưa file vào uploadDir
            Path filePath = Paths.get(uploadDir, fileName);

            // Sao chép dữ liệu từ MultipartFile sang file mới tại đường dẫn vừa tạo
            file.transferTo(filePath.toFile());

            // lưu đường dẫn truy cập công khai (trình duyệt sẽ dùng đường dẫn này)
            imagePath = "/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/" + fileName;
        }

        ResponseResult<User> result = userService.editProfileUserImage(userResponseSession.getEmail(), imagePath, file);
        System.out.println("hello" + result.getStatus());
        if (result.isActive()) {
            User userEntity = userService.loadUserByEmail(userResponseSession.getEmail());
            UserResponse userResponse = userService.getUser(userEntity);
            session.setAttribute("account", userResponse);
            return "redirect:/profileUser";
        } else {
            session.setAttribute("errorUpdateImage", result.getMessage());
            return "redirect:/profileUser";
        }
    }

    @PostMapping("/profileUserEdit/password")
    public String editProfileUserPassword(@RequestParam("oldPassword") String oldPassword,
                                          @RequestParam("newPassword") String newPassword) {
        UserResponse userResponseSession = (UserResponse) session.getAttribute("account");
        ResponseResult<User> checkOldPassword = userService.checkOldPassword(userResponseSession.getEmail(), oldPassword);
        if (!checkOldPassword.isActive()) {
//            System.out.println("hello"+checkOldPassword.getMessage());
            session.setAttribute("errorChangePassword", checkOldPassword.getMessage());
            return "redirect:/profileUser";
        } else {
            ResponseResult<User> result = userService.changePassword(userResponseSession.getEmail(), newPassword);
            if (result.isActive()) {
//                System.out.println("hello"+result.getMessage());
                session.setAttribute("successChangePassword", result.getMessage());
                return "redirect:/profileUser";
            } else {
//                System.out.println("hello"+checkOldPassword.getMessage());
                session.setAttribute("errorChangePassword", result.getMessage());
                return "redirect:/profileUser";
            }

        }
    }

}
