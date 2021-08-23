package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorsRepository {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorsRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
