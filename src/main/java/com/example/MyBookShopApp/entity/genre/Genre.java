package com.example.MyBookShopApp.entity.genre;

import com.example.MyBookShopApp.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Жанры (дерево)
 */

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование жанра
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  мнемонический код жанра, используемый в ссылках на страницу данного жанра
    private String slug;

    @ManyToOne
    @JoinColumn(name = "parent_id",
            columnDefinition = "INT DEFAULT NULL")
//  идентификатор родительского жанра или NULL, если жанр является корневым
    private Genre parentGenre;

    @OneToMany(mappedBy = "parentGenre")
//  список под жанров данного жанра
    private List<Genre> subGenreList;

    @ManyToMany(mappedBy = "genres")
//  список книг относящихся к данному жанру
    private Set<Book> books;
}