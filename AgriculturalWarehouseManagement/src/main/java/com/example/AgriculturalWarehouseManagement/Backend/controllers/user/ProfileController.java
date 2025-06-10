package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @GetMapping("/profileUser/{token}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable String token ) {


        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Nếu không có token, trả về lỗi 401
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Nếu token không hợp lệ, trả về lỗi 401
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userService.loadUserByEmail(email);
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Nếu không tìm thấy người dùng, trả về lỗi 401
        }

        // Chuyển đổi thông tin người dùng thành đối tượng phản hồi (response)
//        UserResponse userResponse = userService.mapToUserResponse(userEntity);

        // Trả về thông tin người dùng
//        return ResponseEntity.ok(userResponse);
        return null;
    }
}
