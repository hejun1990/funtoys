package com.funtoys.backend.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hejun on 2017/7/3.
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    @Value("${project_url}")
    private String projectUrl;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 不拦截ajax请求
            super.postHandle(request, response, handler, modelAndView);
        } else {
            logger.info("BaseInterceptor-->postHandle：{}", request.getRequestURI());
            if (modelAndView != null) {
                ModelMap modelMap = modelAndView.getModelMap();
                modelMap.put("base_url", projectUrl);
            }
        }
    }
}
