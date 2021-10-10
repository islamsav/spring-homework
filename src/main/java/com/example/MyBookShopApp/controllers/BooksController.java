package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookRatingDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.ResourceStorage;
import com.example.MyBookShopApp.service.book.BookRatingService;
import com.example.MyBookShopApp.service.book.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Slf4j
@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final BookRatingService bookRatingService;
    private final ResourceStorage storage;


    @Autowired
    public BooksController(BooksService booksService, BookRatingService bookRatingService, ResourceStorage storage) {
        this.booksService = booksService;
        this.bookRatingService = bookRatingService;
        this.storage = storage;
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable String slug, Model model) {
        BookEntity book = booksService.getBookBySlug(slug);
        BookRatingDto bookRating = bookRatingService.bookRating(book.getId());  //TODO  <span class="Rating-stars"><span class="Rating-star Rating-star_view">
//        Rating-star_view   выделяет

        model.addAttribute("slugBook", book);
        model.addAttribute("bookRating", bookRating);
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

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) {
        Path path = storage.getBookFilePath(hash);
        log.info("book file path: {}", path);
        MediaType mediaType = storage.getFileBookMime(hash);
        log.info("book file mime type: {}", mediaType);
        byte[] data = storage.getBookFileByteArray(hash);
        log.info("book file len: {}", data.length);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .body(new ByteArrayResource(data));
    }
}
