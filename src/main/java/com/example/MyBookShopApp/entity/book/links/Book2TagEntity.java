package com.example.MyBookShopApp.entity.book.links;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book2tag")
public class Book2TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "tag_id")
    private Integer tagId;

}
