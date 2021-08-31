package com.example.MyBookShopApp.entity.book.link;

import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Привязка книг к жанрам
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book2genre")
public class Book2Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  идентификатор связи
    private Integer id;

    @Column(name = "book_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор книги
    private Integer bookId;

    @Column(name = "genre_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор жанра
    private Integer genreId;
}