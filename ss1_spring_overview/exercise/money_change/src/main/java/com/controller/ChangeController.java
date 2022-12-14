package com.controller;

import com.service.impl.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeController {
    @Autowired
    private ChangeService service;

    @GetMapping
    public String toChange() {
        return "/index";
    }

    @PostMapping("/change")
    public ModelAndView toResult(@RequestParam String amount, @RequestParam String rate) {
        return new ModelAndView("/index",
                "result", service.change(Double.parseDouble(amount), Double.parseDouble(rate)));
    }
}
