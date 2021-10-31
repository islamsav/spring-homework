package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AuthorRepositoryTests {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void findAuthorByAuthorId() {
        AuthorEntity authorEntityById = authorRepository.getAuthorEntityById(1);
        assertNotNull(authorEntityById);
        assertEquals(authorEntityById.getName(), "Hailee Stanmore");
        assertEquals(authorEntityById.getDescription(), "Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.");
    }
}
