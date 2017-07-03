package com.funtoys.backend.interceptor;

import com.funtoys.service.domain.generation.AccountInfo;
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

    @Value("${project_name}")
    private String projectName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截路径：{}", request.getRequestURI());
        AccountInfo accountInfo = (AccountInfo) request.getSession().getAttribute("accountInfo");
        if (accountInfo == null) {
            response.sendRedirect("/login/page");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put("base_url", projectName);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
