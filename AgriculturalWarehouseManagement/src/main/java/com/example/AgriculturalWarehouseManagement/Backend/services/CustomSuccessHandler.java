package com.example.AgriculturalWarehouseManagement.Backend.services;

import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    public CustomSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    protected String determineTargetUrl(final Authentication authentication) {

        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/");
        roleTargetUrlMap.put("ROLE_ADMIN", "/admin");
        roleTargetUrlMap.put("ROLE_SELLER", "/");
        roleTargetUrlMap.put("ROLE_WAREHOUSE", "/warehouse");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        String email = authentication.getName();
        User user = userService.findByEmail(email);
        if(user != null){
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("email", user.getEmail());
//            session.setAttribute("avatar", user.getImage());
        }
    }

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);

        clearAuthenticationAttributes(request, authentication);
    }

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        // 1. Huỷ phiên cũ nếu có (đảm bảo clean)
//        HttpSession oldSession = request.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
//        // 2. Tạo session mới
//        HttpSession session = request.getSession(true);
//
//        // 3. Lấy thông tin người dùng
//        String email = authentication.getName();
//        User user = userService.findByEmail(email);
//
//        // 4. Gán thông tin người dùng vào session
//        if (user != null) {
//            session.setAttribute("fullName", user.getFullName());
//            session.setAttribute("email", user.getEmail());
//            session.setAttribute("avatar", user.getImage());
//            session.setAttribute("address", user.getAddress());
//            session.setAttribute("phone", user.getPhone());
//        }
//
//        // 5. Xoá lỗi xác thực cũ nếu có
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//
//        // 6. Redirect tới trang phù hợp
//        String targetUrl = determineTargetUrl(authentication);
//        if (!response.isCommitted()) {
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//        }
//    }

}
