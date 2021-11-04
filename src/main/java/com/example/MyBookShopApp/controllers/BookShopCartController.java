package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.annotations.RemoveToCartLoggable;
import com.example.MyBookShopApp.annotations.UserAddSlugToCartLoggable;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.book.BooksService;
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

@Controller
@RequestMapping("/books")
public class BookShopCartController {

    private final BooksService booksService;

    @Autowired
    public BookShopCartController(BooksService booksService) {
        this.booksService = booksService;
    }

    @ModelAttribute(name = "bookCart")
    public List<BookEntity> bookCart() {
        return new ArrayList<>();
    }

    @GetMapping("/cart")
    public String handleCartRequest(@CookieValue(value = "cartContent", required = false) String cartContent, Model model) {
        if (cartContent == null || cartContent.isEmpty()) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            cartContent = cartContent.startsWith("/") ? cartContent.substring(1) : cartContent;
            cartContent = cartContent.endsWith("/") ? cartContent.substring(0, cartContent.length() - 1) : cartContent;
            String[] slugs = cartContent.split("/");
            List<BookEntity> booksFromCookieSlugs = booksService.booksSlugs(slugs);
            model.addAttribute("bookCart", booksFromCookieSlugs);
        }
        return "cart";
    }

    @UserAddSlugToCartLoggable
    @PostMapping("/changeBookStatus/{slug}")
    public String handleChangeBookStatus(
            @PathVariable String slug,
            @CookieValue(name = "cartContent", required = false) String cartContent,
            HttpServletResponse response,
            Model model) {
        if (cartContent == null || cartContent.isEmpty()) {
            Cookie cookie = new Cookie("cartContent", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", true);
        } else if (!cartContent.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(cartContent).add(slug);
            Cookie cookie = new Cookie("cartContent", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        }
        return "redirect:/books/" + slug;
    }

    @RemoveToCartLoggable
    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    public String handleRemoveBook(
            @PathVariable String slug,
            @CookieValue(name = "cartContent", required = false) String cartContent,
            HttpServletResponse response,
            Model model) {
        if (cartContent != null && !cartContent.isEmpty()) {
            List<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContent.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("cartContent", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else {
            model.addAttribute("isCartEmpty", true);
        }
        return "redirect:/books/cart";
    }
}
