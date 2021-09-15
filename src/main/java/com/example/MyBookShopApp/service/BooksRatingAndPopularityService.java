package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BooksRatingAndPopularityService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksRatingAndPopularityService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookEntity> getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        // добавить логику по популярности
        Page<BookEntity> pageByStatus = bookRepository.booksByStatusCounts(nextPage);
        List<BookEntity> list = pageByStatus.toList();
        List<BookEntity> rating = new ArrayList<>();
        Map<Integer, String> idStatusMap = new HashMap<>();
        list.forEach(book -> idStatusMap.put(book.getId(), book.getStatus()));
        Map<BookEntity, Integer> booksRating = new HashMap<>();

        idStatusMap.forEach((k, v) -> {
            if (v.equals("KEPT")) {
                double res = list.stream().filter(e -> e.getId().equals(k)).findFirst().get().getCount() * 0.4;
            }
        });
        return bookRepository.findAll(nextPage);
        // Добавить новое поле rating в BookEntity и на его основе выдавать лист
    }
//    P = B + 0,7*C + 0,4*K,
//    B — количество пользователей, купивших книгу,
//    C — количество пользователей, у которых книга находится в корзине,
//    K — количество пользователей, у которых книга отложена.
}
