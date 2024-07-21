package com.srimani7.elibrary.controllers;

import com.srimani7.elibrary.Entity.Book;
import com.srimani7.elibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookRepository bookRepository;

    @Autowired
    public AdminController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    @DeleteMapping("/book")
    public String deleteBook(@RequestParam String isbn) {
        bookRepository.deleteById(isbn);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/update-book")
    public String updateBook(@RequestParam String isbn, Model model) {
        Optional<Book> book = bookRepository.findById(isbn);
        model.addAttribute("book", book.orElse(null));
        return "admin-updatebook";
    }

    @PutMapping("/update-book")
    public String updateBook(@RequestParam("isbn") String isbn,
                             @RequestParam("title") String title,
                             @RequestParam("publisher") String publisher,
                             @RequestParam("author") String author,
                             @RequestParam("publishedYear") int publishedYear,
                             @RequestParam("genre") String genre) {
        Book book = new Book(isbn, title, publisher, author, publishedYear, genre);
        bookRepository.save(book);
        return "redirect:/admin/dashboard";
    }
}
