package com.ss8.controller;

import com.ss8.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());

        return "create";
    }

    @PostMapping("/create")
    public String createNewUser(@Validated @ModelAttribute User user, BindingResult bindingResult) {

        new User().validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create";
        }

        return "result";
    }

}
