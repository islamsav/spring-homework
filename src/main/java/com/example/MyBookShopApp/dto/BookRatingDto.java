package com.example.MyBookShopApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRatingDto {

    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;
    private Integer total;
    private Integer average;
}
