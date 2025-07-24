package com.example.AgriculturalWarehouseManagement.Backend.type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.payos.PayOS;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Configuration
public class SpringbootBackendPayosApplication implements WebMvcConfigurer {

    @Value("4aa5e179-ebc2-4a90-a77c-079be20e77d6")
    private String clientId;

    @Value("f5d792b0-8230-48e5-98b2-1e3cf3072b33")
    private String apiKey;

    @Value("41192edf10fcfc68b0a0ccc13125c75a012c308f157ced99c89b4fcec7053252")
    private String checksumKey;

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600); // Max age of the CORS pre-flight request
    }

    @Bean
    public PayOS payOS() {
        return new PayOS(clientId, apiKey, checksumKey);
    }
}
