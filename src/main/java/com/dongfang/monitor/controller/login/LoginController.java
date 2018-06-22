package com.dongfang.monitor.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/toLogin")
    public String toLogin(Model model){
        return "/login";
    }



}
