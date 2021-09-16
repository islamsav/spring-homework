package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.book.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SearchController {

    private final BooksService booksService;

    @Autowired
    public SearchController(BooksService booksService) {
        this.booksService = booksService;
    }

    @ModelAttribute("bookList")
    public List<BookEntity> bookList() {
        return booksService.getAllBooks();
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search/index";
    }
}
