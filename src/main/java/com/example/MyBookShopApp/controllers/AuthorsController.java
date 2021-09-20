package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.author.AuthorsService;
import com.example.MyBookShopApp.service.book.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorsService authorsService;
    private final BooksService booksService;

    @Autowired
    public AuthorsController(AuthorsService authorsService, BooksService booksService) {
        this.authorsService = authorsService;
        this.booksService = booksService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorsService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "/authors/index";
    }

    @ModelAttribute("authorsPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }

    @GetMapping(value = "/authors/slug/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String authorPageById(@PathVariable Integer id, Model model) {
        Page<BookEntity> bookEntities = booksService.booksByAuthor(0, 6, id);
        model.addAttribute("author", authorsService.getAuthorByAuthorId(id));
        model.addAttribute("authorBooks", bookEntities.getContent());
        return "/authors/slug";
    }
}
