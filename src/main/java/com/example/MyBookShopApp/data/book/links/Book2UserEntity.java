package com.example.MyBookShopApp.data.book.links;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "INT NOT NULL")
    private int typeId;

    @Column(columnDefinition = "INT NOT NULL")
    private int bookId;

    @Column(columnDefinition = "INT NOT NULL")
    private int userId;
}
