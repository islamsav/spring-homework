package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entities.Book;
import com.example.MyBookShopApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularController {

    private final BooksService booksService;

    @Autowired
    public PopularController(BooksService booksService) {
        this.booksService = booksService;
    }

    @ModelAttribute("bookList")
    public List<Book> bookList() {
        return booksService.getAllBooks();
    }

    @GetMapping("/books/popular")
    public String popularPage() {
        return "books/popular";
    }

    @ModelAttribute("popularPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}
