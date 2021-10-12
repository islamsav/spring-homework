package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookRateDto;
import com.example.MyBookShopApp.dto.BookRatingDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import com.example.MyBookShopApp.service.ResourceStorage;
import com.example.MyBookShopApp.service.book.BookRatingService;
import com.example.MyBookShopApp.service.book.BookReviewService;
import com.example.MyBookShopApp.service.book.BooksService;
import com.example.MyBookShopApp.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;
    private final BookRatingService bookRatingService;
    private final BookReviewService bookReviewService;
    private final ResourceStorage storage;
    private final UserService userService;

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable String slug, Model model) {
        BookEntity book = booksService.getBookBySlug(slug);
        BookRatingDto bookRating = bookRatingService.bookRating(book.getId());
        model.addAttribute("slugBook", book);
        model.addAttribute("bookRating", bookRating);
        return "books/slug";
    }

    @PostMapping("/addReview/{slug}")
    public String addReview(@PathVariable String slug,
                            @RequestParam String reviewAuthor,
                            @RequestParam String reviewText,
                            @RequestParam Integer ratingReview) {
        BookEntity book = booksService.getBookBySlug(slug);
        BookReviewEntity.BookReviewEntityBuilder bookReviewEntity = BookReviewEntity.builder();
        UserEntity user = new UserEntity();
        user.setHash(String.valueOf(book.hashCode()));
        user.setBalance(new Random().nextInt(1000));
        user.setName(reviewAuthor);
        user.setRegTime(LocalDateTime.now());
        user.setReviews(Collections.emptySet());
        userService.save(user);
        bookReviewEntity
                .bookId(book.getId())
                .rating(ratingReview)
                .text(reviewText)
                .time(Date.valueOf(LocalDate.now()))
                .user(user)
                .userName(reviewAuthor);

        bookReviewService.save(bookReviewEntity.build());
        return "redirect:/books/" + slug;
    }

    @PostMapping(value = "/changeBookStatus/vote/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BookRateDto> changeRating(
            @PathVariable Integer bookId,
            @RequestParam Integer value) {
        bookRatingService.saveRate(bookId, value);
        return ResponseEntity.ok(new BookRateDto(true));
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
