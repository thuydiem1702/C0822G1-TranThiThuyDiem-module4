package com.casestudy.case_study.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String toHomePage() {
        return "/home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "security/myLogin";
    }

}