package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookToRemove {

    private Integer id;
    private String author;
    private String title;
    private Integer size;
}
