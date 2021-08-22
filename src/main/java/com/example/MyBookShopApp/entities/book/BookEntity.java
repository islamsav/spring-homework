package com.example.MyBookShopApp.entities.book;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    private Date pubDate = new Date();
    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    private int isBestseller;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;
    @Column(columnDefinition = "VARCHAR(255)")
    private String image;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INT NOT NULL")
    private int price;
    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    private int discount;
}