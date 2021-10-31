package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BooksRepository {

    private final BookRepository bookRepository;

    @Autowired
    public BooksRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void bookById() {
        BookEntity bookEntityById = bookRepository.findBookEntityById(1);
        assertNotNull(bookEntityById);
        assertEquals(bookEntityById.getDescription(), "Etiam pretium iaculis justo.");
        assertEquals(bookEntityById.getTitle(), "Randy and the Mob");
    }
}
