package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorsRepository {

    private final AuthorCrudRepository authorCrudRepository;

    @Autowired
    public AuthorsRepository(AuthorCrudRepository authorCrudRepository) {
        this.authorCrudRepository = authorCrudRepository;
    }

    public List<Author> getAuthors() {
        return authorCrudRepository.getAllAuthors();
    }
}
