package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCrudRepository extends JpaRepository<Book, Integer> {

    @Query("from Book")
    List<Book> getAllBooks();
}
