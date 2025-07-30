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

@WebFilter("/shipper/*") // Áp dụng filter cho tất cả các trang
public class ShipperRoleFilter implements Filter {

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

        // Lấy đối tượng User từ session (account)
        UserResponse account = (UserResponse) session.getAttribute("accountShipper");

        // Kiểm tra nếu không có tài khoản hoặc tài khoản không có quyền ADMIN
        if (account == null || !account.getRole().getRoleName().equalsIgnoreCase("SHIPPER")) {

            session.invalidate();
            responseHttp.sendRedirect(requestHttp.getContextPath() + "/login");
            return;
        }

        // Nếu có quyền ADMIN hoặc SELLER, tiếp tục cho phép yêu cầu đi qua
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên nếu cần
    }
}
