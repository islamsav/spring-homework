package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<Book2RatingEntity, Integer> {

    @Query()
    List<Integer> bookRatingByBookId(Integer id);

}
