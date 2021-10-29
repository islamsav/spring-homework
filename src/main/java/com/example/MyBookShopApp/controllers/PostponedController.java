package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.BookstoreUserRegisterService;
import com.example.MyBookShopApp.service.book.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class PostponedController {

    private final BooksService booksService;

    @ModelAttribute("postponedBooks")
    public List<BookEntity> postponedBooks() {
        return new ArrayList<>();
    }

    @GetMapping("/postponed")
    public String handlePostponeRequest(
            @CookieValue(value = "postponeContent", required = false) String postponeContent,
            Model model) {
        if (postponeContent == null || postponeContent.isEmpty()) {
            model.addAttribute("isPostponeEmpty", true);
        } else {
            model.addAttribute("isPostponeEmpty", false);
            postponeContent = postponeContent.startsWith("/") ? postponeContent.substring(1) : postponeContent;
            postponeContent = postponeContent.endsWith("/") ? postponeContent.substring(0, postponeContent.length() - 1) : postponeContent;
            String[] slugs = postponeContent.split("/");
            List<BookEntity> booksFromCookieSlugs = booksService.booksSlugs(slugs);
            model.addAttribute("postponedBooks", booksFromCookieSlugs);
        }
        return "postponed";
    }

    @PostMapping("/changeBookStatus/postpone/{slug}")
    public String handleChangeBookStatus(
            @PathVariable String slug,
            @CookieValue(name = "postponeContent", required = false) String postponeContent,
            HttpServletResponse response,
            Model model) {
        if (postponeContent == null || postponeContent.isEmpty()) {
            Cookie cookie = new Cookie("postponeContent", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponeEmpty", true);
        } else if (!postponeContent.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponeContent).add(slug);
            Cookie cookie = new Cookie("postponeContent", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponeEmpty", false);
        }
        return "redirect:/books/" + slug;
    }

    @PostMapping("/changeBookStatus/postpone/remove/{slug}")
    public String handleRemoveBook(
            @PathVariable String slug,
            @CookieValue(name = "postponeContent", required = false) String postponeContent,
            HttpServletResponse response,
            Model model) {
        if (postponeContent != null && !postponeContent.isEmpty()) {
            List<String> cookieBooks = new ArrayList<>(Arrays.asList(postponeContent.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("postponeContent", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponeEmpty", false);
        } else {
            model.addAttribute("isPostponeEmpty", true);
        }
        return "redirect:/books/postponed";
    }
}
