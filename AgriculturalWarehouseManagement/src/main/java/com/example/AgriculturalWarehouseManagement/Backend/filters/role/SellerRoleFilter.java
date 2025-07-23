package com.example.AgriculturalWarehouseManagement.Backend.filters.role;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/seller-dashboard/*") // Áp dụng filter cho các trang bắt đầu với "/seller-dashboard"
public class SellerRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Cast request và response thành HttpServletRequest và HttpServletResponse để truy cập session
        HttpServletRequest requestHttp = (HttpServletRequest) request;
        HttpServletResponse responseHttp = (HttpServletResponse) response;
        HttpSession session = requestHttp.getSession();

        // Lấy đối tượng Seller từ session (accountSeller)
        UserResponse accountSeller = (UserResponse) session.getAttribute("accountSeller");

        // Kiểm tra nếu tài khoản SELLER không tồn tại hoặc không có quyền SELLER
        if (accountSeller == null || !accountSeller.getRole().getRoleName().equalsIgnoreCase("SELLER")) {
            // Nếu không phải SELLER, chuyển hướng đến trang login
            session.invalidate();
            responseHttp.sendRedirect(requestHttp.getContextPath() + "/login");
            return; // Dừng filter, không tiếp tục xử lý request
        }

        // Nếu có quyền SELLER, tiếp tục cho phép yêu cầu đi qua
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên nếu cần
    }
}
