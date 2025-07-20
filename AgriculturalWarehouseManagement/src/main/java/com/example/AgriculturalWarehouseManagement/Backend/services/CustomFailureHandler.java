package com.example.AgriculturalWarehouseManagement.Backend.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Đăng nhập thất bại. Vui lòng thử lại.";

        if (exception instanceof UsernameNotFoundException) {
            errorMessage = exception.getMessage(); // lấy thông báo bạn throw ra
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Sai email hoặc mật khẩu.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Tài khoản đã bị vô hiệu hóa.";
        }

        request.getSession().setAttribute("error", errorMessage);
        response.sendRedirect("/AgriculturalWarehouseManagementApplication/login?error");
    }
}
