package com.controller;

import com.service.impl.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping
    public String toIndex() {
        return "/index";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam String firstParam, @RequestParam String secondParam,
                            @RequestParam String operator, Model model) {

        double result = calculatorService.calculate(Double.parseDouble(firstParam),
                Double.parseDouble(secondParam), operator);
        model.addAttribute("result", result);
        return "/index";
    }
}
