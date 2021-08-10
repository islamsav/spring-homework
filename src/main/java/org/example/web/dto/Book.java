package org.example.web.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Book {

    @NotNull
    private Integer id;
    @Size(min = 1, max = 250)
    private String author;
    @Size(min = 1, max = 250)
    private String title;
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer size;
}
