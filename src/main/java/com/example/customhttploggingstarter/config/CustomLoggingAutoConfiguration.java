package com.example.customhttploggingstarter.config;

import com.example.customhttploggingstarter.interceptor.CustomLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Автоконфигурация стартера.
 */
@AutoConfiguration
@EnableConfigurationProperties(CustomLoggingProperties.class)
@ConditionalOnProperty(prefix = "logging.interceptor", value = "enabled", havingValue = "true")
public class CustomLoggingAutoConfiguration {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(CustomLoggingInterceptor.class);
    }

    @Bean
    public CustomLoggingInterceptor customLoggingInterceptor() {
        return new CustomLoggingInterceptor(logger());
    }

    @Bean
    public CustomWebConfiguration webConfig() {
        return new CustomWebConfiguration(customLoggingInterceptor());
    }
}
