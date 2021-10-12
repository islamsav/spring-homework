package com.example.MyBookShopApp.entity.book.review;

import com.example.MyBookShopApp.entity.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private Date time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    private Integer rating;

    @OneToMany(mappedBy = "bookReview", cascade = CascadeType.ALL)
    private List<BookReviewLikeEntity> bookReviewLikes = new ArrayList<>();

    @ManyToOne
    private UserEntity user;
}
