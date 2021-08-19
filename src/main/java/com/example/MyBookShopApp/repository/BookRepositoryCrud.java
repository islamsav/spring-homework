package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryCrud extends JpaRepository<Book, Integer> {
}
