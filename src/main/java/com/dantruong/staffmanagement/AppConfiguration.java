package com.dantruong.staffmanagement.config; // Check lại package cho đúng với file của bạn

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình để đường dẫn /uploads/** sẽ trỏ vào thư mục uploads dưới máy
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}