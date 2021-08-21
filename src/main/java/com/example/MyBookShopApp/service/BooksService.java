package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        return booksRepository.getBooksData();
    }
}
