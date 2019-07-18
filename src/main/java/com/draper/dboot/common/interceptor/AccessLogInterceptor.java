package com.draper.dboot.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录访问请求
 *
 * @author draper_hxy
 */
@Slf4j
public class AccessLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        printLog(request, response, handler);
        return true;
    }

    private void printLog(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String controller = handlerMethod.getBean().getClass().getName();
        log.info("URI:\t{}", request.getRequestURI());
        log.info("Controller:\t{}", getCore(controller));
        log.info("method:\t{}", handlerMethod.getMethod().getName());
    }

    private String getCore(String controller) {
        String[] s = controller.split("\\.");
        return s[s.length - 1];
    }
}
