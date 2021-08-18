package com.example.MyBookShopApp.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private String author;
    private String title;
    private String priceOld;
    private String price;
}
