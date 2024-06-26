package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.Entity.User;
import com.srimani7.elibrary.repositories.BookRepository;
import com.srimani7.elibrary.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model, HttpSession httpSession) {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        if (user != null) {
            httpSession.setAttribute("role", "user");
            httpSession.setAttribute("userId", user.getId());
            return "redirect:/user/dashboard"; // Corrected path
        } else {
            model.addAttribute("error", "Wrong credentials");
            return "redirect:/";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) String search, Model model) {
        if (search != null) {
            model.addAttribute("books", bookRepository.findByTitleContains(search));
        }
        else model.addAttribute("books", bookRepository.findAll());
        return "user-dashboard";
    }

    @GetMapping("/register")
    public String registration() {
        return "user-register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String userName, @RequestParam String password, Model model) {
        User existingUser = userRepository.findByUserName(userName);
        if(existingUser != null) {
            model.addAttribute("error", "Username already exists");
            return "redirect:/";
        } else {
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            userRepository.save(newUser);
            model.addAttribute("success", "Registration successfully completed");
            return "redirect:/";
        }
    }
}
