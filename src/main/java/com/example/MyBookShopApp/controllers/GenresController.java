package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.service.book.BooksService;
import com.example.MyBookShopApp.service.genre.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books/genre")
public class GenresController {

    private final BooksService booksService;
    private final GenresService genresService;

    @Autowired
    public GenresController(BooksService booksService, GenresService genresService) {
        this.booksService = booksService;
        this.genresService = genresService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String genrePage(@PathVariable Integer id, Model model) {
        model.addAttribute("refreshid", id);
        model.addAttribute("genresBook", booksService.booksToGenre(id));
        return "genres/slug";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BooksPageDto genresPage(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        return new BooksPageDto(booksService.booksToGenre(id, offset, limit).getContent());
    }

    @ModelAttribute("allGenres")
    public List<GenreEntity> allGenres() {
        return genresService.allGenres();
    }

    @ModelAttribute("genresPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }
}