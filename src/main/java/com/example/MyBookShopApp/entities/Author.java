package com.example.MyBookShopApp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
