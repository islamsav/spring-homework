package com.example.MyBookShopApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class BookDto {
    private Integer id;
    private String description;
    private Integer discount;
    private String image;
    private Boolean isBestseller;
    private Integer price;
    private LocalDate pubDate;
    private String slug;
    private String title;
    private Double rating;
}
