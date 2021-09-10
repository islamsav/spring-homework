package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

/**
 * Новинки
 */
@Slf4j
@Controller
public class RecentController {

    private final BooksService booksService;

    @Autowired
    public RecentController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(value = "/books/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BooksPageDto getRecentBooks(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "from", required = false) Date from,
            @RequestParam(value = "to", required = false) Date to) {
        log.info("from={}, to={}", from, to);
        return new BooksPageDto(booksService.getPageOfRecentBooks(offset, limit).getContent());
    }

    @GetMapping(value = "/books/recent")
    public String newItemsPage() {
        return "books/recent";
    }

    @ModelAttribute("bookList")
    public List<BookEntity> bookList() {
        return booksService.getAllBooks();
    }

    @ModelAttribute("recentPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}
