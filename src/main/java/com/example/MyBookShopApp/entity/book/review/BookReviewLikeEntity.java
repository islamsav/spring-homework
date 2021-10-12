package com.example.MyBookShopApp.entity.book.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "book_review_like")
public class BookReviewLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "review_id", columnDefinition = "INT NOT NULL", insertable = false, updatable = false)
    private int reviewId;

    @Column(name = "user_id")
    private int userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

    @ManyToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private BookReviewEntity bookReview;
}
