package com.example.customhttploggingstarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки логирования.
 */
@Data
@ConfigurationProperties(prefix = "logging.interceptor")
public class CustomLoggingProperties {

    /**
     * Чекбокс включения логирования.
     */
    private boolean enabled;

    /**
     * Уровень логирования.
     */
    private String level = "INFO";
}
