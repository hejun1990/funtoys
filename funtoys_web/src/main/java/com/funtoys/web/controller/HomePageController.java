package com.funtoys.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hejun on 2017/6/27.
 */
@Controller
public class HomePageController {
    private static Logger logger = LoggerFactory.getLogger(HomePageController.class);

    /**
     * 网站首页
     *
     * @return vm模板页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String home() {
        logger.info("进入网站首页");
        return "backend/home";
    }
}
