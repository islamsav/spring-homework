package com.example.MyBookShopApp.entity.book.link;

import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Привязка авторов к книгам
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book2author")
public class Book2Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
//  id связи
    private Integer id;

    @Column(name = "book_id", columnDefinition = "INT NOT NULL")
//  идентификатор книги
    private Integer bookId;

    @Column(name = "author_id", columnDefinition = "INT NOT NULL")
//  идентификатор автора
    private Integer authorId;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
//  порядковый номер автора
    private Integer sortIndex;
}