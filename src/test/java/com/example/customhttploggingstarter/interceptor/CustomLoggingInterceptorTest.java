package com.example.customhttploggingstarter.interceptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomLoggingInterceptorTest {

    private CustomLoggingInterceptor customLoggingInterceptor;
    private Logger logger;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        logger = Mockito.mock(Logger.class);
        customLoggingInterceptor = new CustomLoggingInterceptor(logger);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void preHandle_returnTrue_success() {
        //given
        request.setRequestURI("/test");
        request.setMethod("GET");
        request.addHeader("Authorization", "Bearer token");

        //then
        boolean result = customLoggingInterceptor.preHandle(request, response, "handler");

        assertTrue(result);
    }

    @Test
    void postHandle_verifyStatus_success() {
        //given
        response.setStatus(200);

        //then
        customLoggingInterceptor.postHandle(request, response, "handler", new ModelAndView());

        verify(logger, times(1)).info("Рест отработал со статусом: {}", response.getStatus());
    }

    @Test
    void afterCompletion_thrownException() {
        //given
        Exception exception = new RuntimeException("Тестирование ошибки");

        //then
        customLoggingInterceptor.afterCompletion(request, response, "handler", exception);

        verify(logger, times(1)).error("Произошла ошибка: ", exception);
    }
}