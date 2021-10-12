package com.example.MyBookShopApp.service.book;

import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookReviewService {

    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewService(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public void save(BookReviewEntity bookReviewEntity) {
        bookReviewRepository.save(bookReviewEntity);
    }
}
