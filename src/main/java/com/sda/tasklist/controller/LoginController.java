package com.sda.tasklist.controller;

import com.sda.tasklist.dto.user.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    ModelAndView getLoginPage() {
        ModelAndView mnv = new ModelAndView("user/login");
        mnv.addObject("loginForm", new LoginForm());
        return mnv;
    }
}

