package com.example.MyBookShopApp.entity.book.link;

import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Привязка книг к тегам
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book2tag")
public class Book2Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор книги
    private Integer bookId;

    @Column(name = "tag_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор тэга
    private Integer tagId;
}