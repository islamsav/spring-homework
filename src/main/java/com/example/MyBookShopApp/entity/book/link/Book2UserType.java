package com.example.MyBookShopApp.entity.book.link;

import com.example.MyBookShopApp.entity.enumiration.Book2UserTypeEnum;
import lombok.*;

import javax.persistence.*;

/**
 * Project name: MyBookShopApp
 * Date: 8/9/2021
 * Author: dishmitov
 * Description:
 * Типы привязок книг к юзерам:
 * Отложена — KEPT
 * В корзине — CART
 * Куплена — PAID
 * В архиве — ARCHIVED
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book2user_type")
public class Book2UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
//  id связи типа книги к юзеру
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  код типа привязки
    private String code;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
//  наименование типа привязки
    @Enumerated(EnumType.STRING)
    private Book2UserTypeEnum name;
}