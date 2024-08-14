package com.example.customhttploggingstarter.config;

import com.example.customhttploggingstarter.interceptor.CustomLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Реализация {@link WebMvcConfigurer}.
 */
@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {

    private final CustomLoggingInterceptor customLoggingInterceptor;

    @Autowired
    public CustomWebConfiguration(CustomLoggingInterceptor customLoggingInterceptor) {
        this.customLoggingInterceptor = customLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customLoggingInterceptor);
    }
}
