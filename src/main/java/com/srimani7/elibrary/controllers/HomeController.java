package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registration() {
        return "user-register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String userName, @RequestParam String password, Model model) {
        var user = userService.registerUser(userName, password, "USER");
        if(user.isEmpty()) {
            model.addAttribute("error", "Username already exists");
            return "redirect:/";
        } else {
            model.addAttribute("success", "Registration successfully completed");
            return "redirect:/";
        }
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "403";
    }
}
