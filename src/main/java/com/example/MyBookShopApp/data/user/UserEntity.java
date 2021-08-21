package com.example.MyBookShopApp.data.user;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;
}
