package com.example.MyBookShopApp.service.genre;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    @Autowired
    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<GenreEntity> allGenres() {
        return genresRepository.findAll();
    }
}
