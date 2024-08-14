package com.example.customhttploggingstarter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для перехвата и логирования HTTP запросов.
 */
@AllArgsConstructor
public class CustomLoggingInterceptor implements HandlerInterceptor {

    private Logger logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("URL запроса: {}", request.getRequestURL());
        logger.info("Тип запроса: {}", request.getMethod());
        logger.info("Заголовки запроса: {}", Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), h -> Collections.list(request.getHeaders(h)))));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("Рест отработал со статусом: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            logger.error("Произошла ошибка: ", ex);
        }
    }
}