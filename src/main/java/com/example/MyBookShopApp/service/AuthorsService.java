package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.Author;
import com.example.MyBookShopApp.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Map<String, List<Author>> getAuthorsMap() {
        return authorsRepository.getAuthorsMap();
    }
}
