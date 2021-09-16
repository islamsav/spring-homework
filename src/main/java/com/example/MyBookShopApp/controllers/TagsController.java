package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.book.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books/tag")
public class TagsController {

    private final BooksService booksService;

    @Autowired
    public TagsController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/{id}")
    public String tagsPage(Model model, @PathVariable Integer id) {
        model.addAttribute("id", id);
        model.addAttribute("tagName", booksService.getTagNameByTagId(id));
        return "/tags/index";
    }

    @ModelAttribute("booksByTag")
    public List<BookEntity> tagsPage(@PathVariable Integer id) {
        return booksService.getPageOfTagsById(0, 20, id).getContent();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BooksPageDto getPageByOffset(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        return new BooksPageDto(booksService.getPageOfTagsById(offset, limit, id).getContent());
    }
}
