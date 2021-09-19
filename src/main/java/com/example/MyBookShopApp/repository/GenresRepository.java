package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<GenreEntity, Integer> {

}
