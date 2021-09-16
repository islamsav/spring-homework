package com.example.MyBookShopApp.entity.other;

import com.example.MyBookShopApp.entity.book.BookEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "tag")
@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "book2tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> books;

    @Formula("(select count(*)\n" +
            "from book b\n" +
            "join book2tag b2t on b.id = b2t.book_id\n" +
            "join tag t on t.id = b2t.tag_id\n" +
            "where b2t.tag_id = id\n)")
    private Integer rating;
}