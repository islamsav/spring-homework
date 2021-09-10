package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.book.Book;
import com.example.MyBookShopApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainPageController {

    private final BooksService booksService;

    @Autowired
    public MainPageController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooks(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return booksService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> recentBooks() {
        return booksService.getPageOfRecentBooks(0, 6).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return booksService.getPageOfPopularBooks(0, 6).getContent();
    }

    @ModelAttribute("mainPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
}
