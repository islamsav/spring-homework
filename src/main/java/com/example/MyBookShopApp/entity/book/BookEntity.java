package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.entity.other.TagEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    @JsonProperty("discount")
    private Integer discount;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(name = "is_bestseller",
            columnDefinition = "SMALLINT NOT NULL")
    private Integer isBestseller;

    @Column(columnDefinition = "INT NOT NULL")
    @JsonProperty("price")
    private Integer price;

    @Column(name = "pub_date", columnDefinition = "DATE NOT NULL")
    private LocalDate pubDate;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @ManyToMany
    @JoinTable(name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnore
    private List<AuthorEntity> authors;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "file_download",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserEntity> usersDownloadedBooks;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> usersBalanceTransactions;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> usersBookReviews;

    @OneToMany(mappedBy = "bookId")
    @JsonIgnore
    private List<BookReviewEntity> bookReviewList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "book2tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private Set<TagEntity> tag;

    @JsonIgnore
    private Double rating;

    @JsonGetter("authors")
    public String author() {
        if (authors.size() > 0) {
            return authors.get(0).getName();
        }
        return null;
    }

    /**
     *
     * @return Цена со скидкой
     */
    @JsonProperty
    public Integer discountPrice() {
        double discountDouble = BigDecimal.valueOf(discount).doubleValue();
        double discountPrice = price * discountDouble / 100;
        return Math.toIntExact(Math.round(price - discountPrice));
    }
}