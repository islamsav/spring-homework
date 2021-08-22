package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entities.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {

    private final BookCrudRepository bookRepository;

    @Autowired
    public BooksRepository(BookCrudRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Books> getBooksData() {
        return bookRepository.getAllBooks();
    }
}
