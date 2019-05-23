package com.gyq.controller;

import com.gyq.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired
    MainService service;

    @RequestMapping("/main")
    public ModelAndView login() {
        return new ModelAndView(service.getView());
    }
}
