package com.example.AgriculturalWarehouseManagement.Backend.configurations;

import com.example.AgriculturalWarehouseManagement.Backend.services.CustomSuccessHandler;
import com.example.AgriculturalWarehouseManagement.Backend.services.CustomUserDetailsService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(User.withUsername("user").password(passwordEncoder().encode("123"))
//                .roles("USER").build());
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsService userDetailsService,
                                                  PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
//        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler(UserService userService) {
        return new CustomSuccessHandler(userService);
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices =
                new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
                        .requestMatchers(
                                "/",
                                "/login",
                                "/BackEnd/assets/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/seller/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers("/seller-dashboard").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers("/warehouse/**").hasAnyRole("ADMIN", "WAREHOUSE")
                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Luôn tạo session mới
                        .invalidSessionUrl("/logout?expired") // Nếu session không hợp lệ thì chuyển hướng
                        .maximumSessions(1) // Chỉ cho phép 1 session đồng thời cho mỗi người dùng
                        .maxSessionsPreventsLogin(false) // Không ngăn người dùng mới đăng nhập (sẽ làm session cũ bị đá ra)
                )
                // Đoạn này là để xóa cookie và hủy session khi logout
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                        .failureUrl("/login?error")
                        .successHandler(new CustomSuccessHandler(userService))
                        .permitAll())
                .exceptionHandling(ex ->
                        ex.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
