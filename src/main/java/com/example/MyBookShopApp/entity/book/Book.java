package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.author.Author;
import com.example.MyBookShopApp.entity.genre.Genre;
import com.example.MyBookShopApp.entity.review.BookReview;
import com.example.MyBookShopApp.entity.tag.Tag;
import com.example.MyBookShopApp.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Project name: MyBookShopApp
 * Date: 6/18/2021
 * Author: dishmitov
 * Description:
 * Основной класс представляющий книгу
 */

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
//  id книги
    private Integer id;

    @Column(columnDefinition = "TEXT")
//  описание книги
    private String description;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
//  скидка в процентах или 0, если её нет
    private Integer discount;

    @Column(columnDefinition = "VARCHAR(255)")
//  изображение обложки
    private String image;

    @Column(name = "is_bestseller",
            columnDefinition = "SMALLINT NOT NULL")
//  книга очень популярна, является бестселлером
    private Boolean isBestseller;

    @Column(columnDefinition = "INT NOT NULL")
//  цена в рублях основная
    private Integer price;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
//  дата публикации
    private LocalDate pubDate;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  мнемонический идентификатор книги
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  название книги
    private String title;

    @ManyToMany
    @JoinTable(name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
//  список авторов данной книги
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
//  список жанров к которым относится данная книга
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "book2tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
//  список тэгов к которым относится данная книга
    private List<Tag> tags;

    @ManyToMany
    @JoinTable(name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей имеющих связь с данной книгой
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(name = "file_download",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//  список пользователей скачавших данную книгу
    private List<UserEntity> usersDownloadedBooks;

    @ManyToMany
    @JoinTable(name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей имеющих транзакции с книгой (списание или зачисление)
    private List<UserEntity> usersBalanceTransactions;

    @ManyToMany
    @JoinTable(name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
//  список пользователей оставивших отзывы о книге
    private List<UserEntity> usersBookReviews;

    @OneToMany(mappedBy = "book")
//  список отзывов данной книги
    private List<BookReview> bookReviewList;
}