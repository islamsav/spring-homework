package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByPubDateBetween(LocalDate pubDateFrom, LocalDate pubDateTo, Pageable pageable);
}
