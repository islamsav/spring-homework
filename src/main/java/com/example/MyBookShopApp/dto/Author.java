package com.example.MyBookShopApp.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
}
