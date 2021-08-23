package com.example.MyBookShopApp.entity.tag;

import com.example.MyBookShopApp.entity.book.Book;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Тэг по которому можно найти книгу
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование тэга
    private String name;

    @ManyToMany(mappedBy = "tags")
//  список книг относящихся к данному тэгу
    private Set<Book> books;
}