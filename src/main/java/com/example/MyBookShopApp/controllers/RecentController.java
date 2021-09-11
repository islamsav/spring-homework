package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.dto.recent.RecentByDateDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Новинки
 */
@Slf4j
@Controller
@RequestMapping("/books/recent")
public class RecentController {

    private final BooksService booksService;

    @Autowired
    public RecentController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    @ResponseBody
    public BooksPageDto getRecentBooks(
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        log.info("from={}, to={}", from, to);
        // TODO сделать SQL для выбора кник по диапазону дат
        return new BooksPageDto(booksService.getPageOfRecentBooks(offset, limit).getContent());
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String newItemsPage(Model model) {
        model.addAttribute("recentByDateDto", new RecentByDateDto());
        return "books/recent";
    }

    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks() {
        return booksService.getPageOfRecentBooks(0, 20).getContent();
    }

    @ModelAttribute("recentPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}
