package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {

    private final BookRepositoryCrud bookRepository;

    @Autowired
    public BooksRepository(BookRepositoryCrud bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }
}
