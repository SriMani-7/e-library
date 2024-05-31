package com.srimani7.elibrary.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public String login(@RequestParam String password, HttpSession httpSession) {
        if (password.equals("1234567890")) {
            httpSession.setAttribute("role", "ADMIN");
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }


}
