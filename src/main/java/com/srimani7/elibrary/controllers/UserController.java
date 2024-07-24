package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.UserService;
import com.srimani7.elibrary.repositories.BookRepository;
import com.srimani7.elibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, @RequestParam(required = false) String search, Model model) {
        if (search != null) {
            model.addAttribute("books", bookRepository.findByTitleContains(search));
        }
        else model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("user", principal.getName());
        return "user-dashboard";
    }
}
