package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("bookData", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/genres";
    }

    @GetMapping("/authors")
    public String authorsPage(Model model) {
        model.addAttribute("bookAuthors", bookService.getAllBooks());
        return "authors/authors";
    }
}
