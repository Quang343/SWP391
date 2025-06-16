package com.example.AgriculturalWarehouseManagement.Backend.configurations;

import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    private final JwtTokenFilter jwtTokenFilter;  // Inject JwtTokenFilter
//
//    // Inject JwtTokenFilter thông qua constructor
//    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
//        this.jwtTokenFilter = jwtTokenFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        String apiPrefix = "http://localhost:8080/";  // Đặt tiền tố đường dẫn API với cổng 8080
//        http
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)  // Thêm bộ lọc JWT trước UsernamePasswordAuthenticationFilter
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(String.format("%s/register", apiPrefix),
//                                String.format("%s/login", apiPrefix))
//                        .permitAll()  // Cho phép mọi người truy cập các endpoint đăng ký và đăng nhập
//                        .requestMatchers(HttpMethod.GET, String.format("%s/roles/**", apiPrefix))
//                        .permitAll()  // Cho phép truy cập GET các endpoint liên quan đến roles
//                        .requestMatchers(HttpMethod.PUT, String.format("%s/profileUser/**", apiPrefix))
//                        .hasAnyRole(Role.USER, Role.ADMIN)  // Chỉ những người dùng có quyền USER hoặc ADMIN mới được phép
//                        .anyRequest().authenticated()  // Tất cả các yêu cầu còn lại đều yêu cầu xác thực
//                );
//
//        return http.build();  // Trả về SecurityFilterChain đã được cấu hình
//    }
//}
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // hoặc List.of("*") nếu dev
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

