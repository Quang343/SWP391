package com.example.AgriculturalWarehouseManagement.Backend.filters;

import com.example.AgriculturalWarehouseManagement.Backend.filters.role.AdminRoleFilter;
import com.example.AgriculturalWarehouseManagement.Backend.filters.role.SellerRoleFilter;
import com.example.AgriculturalWarehouseManagement.Backend.filters.role.ShipperRoleFilter;
import com.example.AgriculturalWarehouseManagement.Backend.filters.role.WareHouseRoleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AdminRoleFilter> adminRoleFilter() {
        FilterRegistrationBean<AdminRoleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminRoleFilter());
        registrationBean.addUrlPatterns("/admin/*");  // Chỉ áp dụng cho các URL của admin
        registrationBean.setOrder(1); // Đặt độ ưu tiên cho filter
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SellerRoleFilter> sellerRoleFilter() {
        FilterRegistrationBean<SellerRoleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SellerRoleFilter());
        registrationBean.addUrlPatterns("/seller-dashboard/*");  // Chỉ áp dụng cho các URL của seller
        registrationBean.setOrder(2); // Đặt độ ưu tiên cho filter
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<WareHouseRoleFilter> wareHouseRoleFilter() {
        FilterRegistrationBean<WareHouseRoleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WareHouseRoleFilter());
        registrationBean.addUrlPatterns("/warehouse/*");  // Chỉ áp dụng cho các URL của seller
        registrationBean.setOrder(3); // Đặt độ ưu tiên cho filter
        return registrationBean;
    }



    // Hoàng thêm phân quyền
    @Bean
    public FilterRegistrationBean<ShipperRoleFilter> shipperRoleFilter() {
        FilterRegistrationBean<ShipperRoleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ShipperRoleFilter());
        registrationBean.addUrlPatterns("/shipper/*");
        registrationBean.setOrder(4);
        return registrationBean;
    }
}
