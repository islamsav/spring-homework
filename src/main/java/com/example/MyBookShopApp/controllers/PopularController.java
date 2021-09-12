package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PopularController {

    private final BooksService booksService;

    @Autowired
    public PopularController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooks(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksService.getPageOfPopularBooks(offset, limit).getContent());
    }

    @ModelAttribute("bookList")
    public List<BookEntity> bookList() {
        return booksService.getAllBooks();
    }

    @GetMapping(value = "/books/popular", produces = MediaType.TEXT_HTML_VALUE)
    public String popularPage() {
        return "books/popular";
    }

    @ModelAttribute("popularPageActiveItem")
    public String activeItem() {
        return "menu-item_ACTIVE";
    }

//    Популярность книги представляет собой неотрицательное число,
//    которое можно рассчитать по следующей формуле:
//    P = B + 0,7*C + 0,4*K,
//    где B — количество пользователей, купивших книгу,
//    C — количество пользователей, у которых книга находится в корзине,
//    а K — количество пользователей, у которых книга отложена.
}
