package com.funtoys.backend.interceptor;

import com.funtoys.service.domain.generation.AccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hejun on 2017/7/4.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${project_url}")
    private String projectUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 不拦截ajax请求
            return true;
        } else {
            logger.info("LoginInterceptor-->preHandle：{}", request.getRequestURI());
            AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute("accountInfo");
            if (accountInfo == null) {
                response.sendRedirect(projectUrl + "/login/page");
                return false;
            }
        }
        return true;
    }
}
