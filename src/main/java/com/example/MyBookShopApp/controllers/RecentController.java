package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Новинки
 */
@Controller
public class RecentController {

    private final BooksService booksService;

    @Autowired
    public RecentController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books/recent")
    public String newItemsPage() {
        return "books/recent";
    }

    @ModelAttribute("bookList")
    public List<Book> bookList() {
        return booksService.getAllBooks();
    }

    @ModelAttribute("recentPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}
