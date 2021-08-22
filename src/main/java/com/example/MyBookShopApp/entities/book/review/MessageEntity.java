package com.example.MyBookShopApp.entities.book.review;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "INT")
    private int userId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String subject;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;
}
