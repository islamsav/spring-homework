package com.example.MyBookShopApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookDto {
    private Integer id;
    private String description;
    private Integer discount;
    private String image;
    private Integer isBestseller;
    private Integer price;
    private LocalDate pubDate;
    private String slug;
    private String title;
    private Double rating;
}
