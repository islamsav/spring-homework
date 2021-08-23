package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {

    private final BookRepository bookRepository;

    @Autowired
    public BooksRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }
}
