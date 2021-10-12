package com.example.MyBookShopApp.service.book;

import com.example.MyBookShopApp.dto.BookRatingDto;
import com.example.MyBookShopApp.entity.book.links.Book2RatingEntity;
import com.example.MyBookShopApp.repository.BookRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRatingService {

    private final BookRatingRepository bookRatingRepository;

    @Autowired
    public BookRatingService(BookRatingRepository bookRatingRepository) {
        this.bookRatingRepository = bookRatingRepository;
    }

    public BookRatingDto bookRating(Integer id) {
        BookRatingDto.BookRatingDtoBuilder bookRatingDto = BookRatingDto.builder();
        List<Integer> bookList = getBookRatingByBookId(id);
        bookRatingDto
                .oneStar(oneStar(bookList))
                .twoStar(twoStar(bookList))
                .threeStar(threeStar(bookList))
                .fourStar(fourStar(bookList))
                .fiveStar(fiveStar(bookList))
                .total(bookList.size())
                .average(averageRating(id));
        return bookRatingDto.build();

    }

    private List<Integer> getBookRatingByBookId(Integer bookId) {
        return bookRatingRepository.bookRatingByBookId(bookId).stream()
                .map(Book2RatingEntity::getRating).collect(Collectors.toList());
    }

    private int oneStar(List<Integer> bookRatingList) {
        return (int) bookRatingList.stream().filter(e -> e == 1).count();
    }

    private int twoStar(List<Integer> bookRatingList) {
        return (int) bookRatingList.stream().filter(e -> e == 2).count();
    }

    private int threeStar(List<Integer> bookRatingList) {
        return (int) bookRatingList.stream().filter(e -> e == 3).count();
    }

    private int fourStar(List<Integer> bookRatingList) {
        return (int) bookRatingList.stream().filter(e -> e == 4).count();
    }

    private int fiveStar(List<Integer> bookRatingList) {
        return (int) bookRatingList.stream().filter(e -> e == 5).count();
    }

    private int averageRating(Integer bookId) {
        return bookRatingRepository.averageRating(bookId);
    }


    /**
     * Созраняет значение рейтинга
     *
     * @param bookId book_id
     * @param value  значение рейтинга от 1 до 5
     */
    public void saveRate(Integer bookId, Integer value) {
        Book2RatingEntity book2RatingEntity = new Book2RatingEntity();
        book2RatingEntity.setBookId(bookId);
        book2RatingEntity.setRating(value);
        bookRatingRepository.save(book2RatingEntity);
    }
}
