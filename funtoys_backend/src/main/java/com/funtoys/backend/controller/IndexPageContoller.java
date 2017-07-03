package com.funtoys.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hejun on 2017/7/3.
 */
@Controller
@RequestMapping("/main")
public class IndexPageContoller {
    private static Logger logger = LoggerFactory.getLogger(IndexPageContoller.class);

    /**
     * 后台管理系统首页
     *
     * @return vm模板页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "backend/index";
    }
}
