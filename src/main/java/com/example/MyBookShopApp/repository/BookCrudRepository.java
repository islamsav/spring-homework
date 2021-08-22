package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCrudRepository extends JpaRepository<Books, Integer> {

    @Query("from Books")
    List<Books> getAllBooks();
}
