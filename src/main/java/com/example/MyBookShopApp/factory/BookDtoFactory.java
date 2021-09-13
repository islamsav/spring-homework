package com.example.MyBookShopApp.factory;

import com.example.MyBookShopApp.dto.BookDto;
import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDtoFactory {

    /**
     * Метод преобразует Entity класс в DTO класс отсекая ненужные поля
     * @param bookEntity BookEntity
     * @return BookDto
     */
    public BookDto createBookDto(BookEntity bookEntity) {
        return BookDto.builder()
                .id(bookEntity.getId())
                .description(bookEntity.getDescription())
                .discount(bookEntity.getDiscount())
                .image(bookEntity.getImage())
                .isBestseller(bookEntity.getIsBestseller())
                .price(bookEntity.getPrice())
                .pubDate(bookEntity.getPubDate())
                .slug(bookEntity.getSlug())
                .title(bookEntity.getTitle())
                .status(bookEntity.getStatus())
                .build();
    }

    /**
     * Метод преобразует List<BookEntity> в List<BookDto> отсекая ненужные поля
     * @param bookEntityList List<BookEntity>
     * @return List<BookDto>
     */
    public List<BookDto> createBookDtoList(List<BookEntity> bookEntityList) {
        return bookEntityList.stream()
                .map(this::createBookDto)
                .collect(Collectors.toList());
    }
}
