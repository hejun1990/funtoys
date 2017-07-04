package com.funtoys.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hejun on 2017/6/28.
 */
@Controller
@RequestMapping("error")
public class ErrorPageController {
    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404() {
        return "error404";
    }
}
