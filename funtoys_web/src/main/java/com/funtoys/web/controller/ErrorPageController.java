package com.funtoys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hejun on 2017/6/27.
 */
@Controller
public class ErrorPageController {
    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404(ModelMap modelMap) {
        modelMap.put("headerName", "error");
        return "error404";
    }
}
