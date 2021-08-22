package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorCrudRepository extends JpaRepository<Authors, Long> {

    @Query("from Authors")
    List<Authors> getAllAuthors();
}
