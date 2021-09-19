package com.example.MyBookShopApp.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books/genre")
public class GenresController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String genresPage() {
        return "genres/index";
    }

    @ModelAttribute("genresPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}
