package com.example.MyBookShopApp.data.book.links;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book2genre")
public class Book2GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "INT NOT NULL")
    private int bookId;

    @Column(columnDefinition = "INT NOT NULL")
    private int genreId;
}
