package com.dongfang.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class TestController {

    @GetMapping("/test")
    public String request(Model model, HttpSession httpSession){
        model.addAttribute("a","阿三发放");
        return "/index";
    }

    @GetMapping("/toLogin")
    public String toLogin(Model model, HttpSession httpSession){
        model.addAttribute("a","阿三发放");
        return "/login";
    }
}
