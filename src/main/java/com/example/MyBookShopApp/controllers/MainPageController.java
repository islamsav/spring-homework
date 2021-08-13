package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.Book;
import com.example.MyBookShopApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainPageController {

    private final BooksService booksService;

    @Autowired
    public MainPageController(BooksService booksService) {
        this.booksService = booksService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
