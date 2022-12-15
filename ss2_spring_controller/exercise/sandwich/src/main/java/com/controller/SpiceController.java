package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpiceController {

    @GetMapping
    public String toIndex() {
        return "/index";
    }

    @PostMapping("/spices")
    public String getSpices(@RequestParam String spices, Model model) {
        model.addAttribute("spices", spices);
        return "/index";
    }
}
