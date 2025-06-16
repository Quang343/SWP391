package com.example.AgriculturalWarehouseManagement.Backend.configurations;

import org.springframework.beans.factory.annotation.Value;              // đúng
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${app.upload.user-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/FrontEnd/assets/images/inner-page/user/**")  // URL ảo
                .addResourceLocations("file:" + uploadDir + "/");   // uploadDir = C:/Users/ADMIN/uploads/user
    }
}

