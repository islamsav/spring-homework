package com.example.MyBookShopApp.service.book;

import com.example.MyBookShopApp.dto.recent.RecentByDateDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@Slf4j
public class BooksService {

    private final BookRepository bookRepository;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;

    @Autowired
    public BooksService(BookRepository bookRepository, BooksRatingAndPopularityService booksRatingAndPopularityService) {
        this.bookRepository = bookRepository;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        // добавить логику по датам
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfRecentBooksWithPubDateBetween(Integer offset, Integer limit, RecentByDateDto recentByDateDto) {
        Pageable nextPage = PageRequest.of(offset, limit);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(recentByDateDto.getFrom(), formatter);
            to = LocalDate.parse(recentByDateDto.getTo(), formatter);
        } catch (DateTimeParseException e) {
            return bookRepository.findAll(nextPage);
        }
        return bookRepository.findAllByPubDateBetween(from, to, nextPage);
    }

    public Page<BookEntity> getPageOfTagsById(Integer offset, Integer limit, Integer id) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntitiesByTagId(id, nextPage);
    }

    public String getTagNameByTagId(Integer id) {
        return bookRepository.tagNameByTagId(id);
    }
}
