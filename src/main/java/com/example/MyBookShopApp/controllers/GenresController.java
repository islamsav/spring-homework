package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GenresController {

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }

    @ModelAttribute("genresPageActiveItem")
    public String activeItem() {
        return "menu-item menu-item_ACTIVE";
    }
}
