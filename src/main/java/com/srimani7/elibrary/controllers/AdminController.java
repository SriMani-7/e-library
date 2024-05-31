package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.Entity.Book;
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
    public String dashboard(@RequestParam(required = false) String search, Model model) {
        if (search != null) {
            model.addAttribute("books", bookRepository.findByTitleContains(search));
        }
        else model.addAttribute("books", bookRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/addbook")
    public String addBookPage() {
        return "admin-addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam("isbn") String isbn,
                          @RequestParam("title") String title,
                          @RequestParam("publisher") String publisher,
                          @RequestParam("author") String author,
                          @RequestParam("publishedYear") int publishedYear,
                          @RequestParam("genre") String genre, Model model) {
        Book book = new Book(isbn, title, publisher, author, publishedYear, genre);
        bookRepository.save(book);
        return "redirect:/admin/dashboard";
    }
}
