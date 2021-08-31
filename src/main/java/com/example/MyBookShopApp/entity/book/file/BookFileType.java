package com.example.MyBookShopApp.entity.book.file;

import com.example.MyBookShopApp.entity.enumiration.BookFileTypeEnum;
import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Типы файлов книг:
 * PDF
 * EPUB
 * FB2
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book_file_type")
public class BookFileType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @Enumerated(EnumType.STRING)
    private BookFileTypeEnum name;
}