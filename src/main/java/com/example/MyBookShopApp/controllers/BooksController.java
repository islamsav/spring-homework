package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.ResourceStorage;
import com.example.MyBookShopApp.service.book.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final ResourceStorage storage;

    @Autowired
    public BooksController(BooksService booksService, ResourceStorage storage) {
        this.booksService = booksService;
        this.storage = storage;
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable String slug, Model model) {
        BookEntity book = booksService.getBookBySlug(slug);
        model.addAttribute("slugBook", book);
        return "books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam MultipartFile file, @PathVariable String slug) {
        String savePath = storage.saveNewBookImage(file, slug);
        BookEntity toUpdate = booksService.getBookBySlug(slug);
        toUpdate.setImage(savePath);
        booksService.save(toUpdate); // сохранение
        return ("redirect:/books/" + slug);
    }
}
