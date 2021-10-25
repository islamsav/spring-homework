package com.example.MyBookShopApp.entity.user;

import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hash;
    private LocalDateTime regTime;
    private int balance;
    private String name;
    private String email;
    private String phone;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<BookReviewEntity> reviews;
}
