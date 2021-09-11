package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BooksService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    public Page<BookEntity> getPageOfRecentBooksWithPubDateBetween(Integer offset, Integer limit, String from, String to) {
        Pageable nextPage = PageRequest.of(offset, limit);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (from.equals("0") || to.equals("0")) {
           return bookRepository.findAll(nextPage);
        }
        return bookRepository.findAllByPubDateBetween(LocalDate.parse(from, formatter), LocalDate.parse(to, formatter), nextPage);
    }

    public Page<BookEntity> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        // добавить логику по популярности
        return bookRepository.findAll(nextPage);
    }
}
