package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query(value = "select a.id, a.name, a.description, a.slug, a.photo \n" +
            "from author a \n" +
            "where a.id = :id", nativeQuery = true)
    AuthorEntity getAuthorEntityById(Integer id);
}
