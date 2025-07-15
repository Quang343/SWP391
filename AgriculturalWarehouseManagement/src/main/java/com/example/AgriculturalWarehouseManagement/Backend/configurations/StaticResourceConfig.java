package com.example.AgriculturalWarehouseManagement.Backend.configurations;

import org.springframework.beans.factory.annotation.Value;              // đúng
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cho phép truy cập ảnh thông qua URL: /Frontend/assets/images/abc.jpg
        registry
                .addResourceHandler("/seller/**")
                .addResourceLocations("file:" + uploadDir + "/Seller/");
        //Warehouse
        registry.addResourceHandler("warehouse/**")
                .addResourceLocations("file:" + uploadDir + "/Warehouse");

        registry.addResourceHandler("/admin/**")
                .addResourceLocations("file:" + uploadDir + "/Admin");

        registry.addResourceHandler("/blog/**")
                .addResourceLocations("file:" + uploadDir + "/Blog");

        registry.addResourceHandler("/FrontEnd/assets/images/inner-page/user/**")  // URL ảo
                .addResourceLocations("file:" + uploadDir + "/User");
    }

}

