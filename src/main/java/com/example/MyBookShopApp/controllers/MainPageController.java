package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BooksPageDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.other.TagEntity;
import com.example.MyBookShopApp.service.book.BooksRatingAndPopularityService;
import com.example.MyBookShopApp.service.book.BooksService;
import com.example.MyBookShopApp.service.tag.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainPageController {

    private final BooksService booksService;
    private final TagService tagService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;


    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooks(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit) {
        return new BooksPageDto(booksService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks() {
        return booksService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @ModelAttribute("tags")
    public List<TagEntity> tags() {
        return tagService.tagsByRating();
    }

    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks() {
        return booksService.getPageOfRecentBooks(0, 6).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks() {
        return booksRatingAndPopularityService.getPageOfPopularBooks(0, 6).getContent();
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
