package com.example.AgriculturalWarehouseManagement.Backend.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SupplierConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("AgriculturalWarehouseManagement/BackEnd/assets/imgproject/**")
                .addResourceLocations("file:D:/Uploads/logos/");
    }
}

