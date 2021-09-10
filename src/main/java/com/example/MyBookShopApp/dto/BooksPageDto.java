package com.example.MyBookShopApp.dto;

import com.example.MyBookShopApp.entity.book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksPageDto {

    private Integer count;
    private List<BookEntity> books;

    public BooksPageDto(List<BookEntity> books) {
        this.books = books;
        this.count = books.size();
    }
}
