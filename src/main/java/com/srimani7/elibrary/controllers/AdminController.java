package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.repositories.BookRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookRepository bookRepository;

    @Autowired
    public AdminController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, HttpSession httpSession) {
        if (password.equals("1234567890")) {
            httpSession.setAttribute("role", "ADMIN");
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/addbook")
    public String addBookPage() {
        return "admin-addbook";
    }
}
