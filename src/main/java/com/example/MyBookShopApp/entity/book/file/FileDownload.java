package com.example.MyBookShopApp.entity.book.file;

import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Количество скачиваний книги юзером количество скачиваний книги юзером
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "file_download")
public class FileDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  идентификатор связи количества скачиваний книги юзером
    private Integer id;

    @Column(name = "book_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор скачанной книги
    private Integer bookId;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 1")
//  количество скачиваний
    private Integer count;

    @Column(name = "user_id",
            columnDefinition = "INT NOT NULL")
//  идентификатор пользователя, скачавшего книгу
    private Integer userId;
}