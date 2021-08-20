package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorCrudRepository extends JpaRepository<Author, Long> {

    @Query("from Author")
    List<Author> getAllAuthors();
}
