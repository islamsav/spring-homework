package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<Book2RatingEntity, Integer> {

    @Query("FROM Book2RatingEntity AS b2r " +
            "JOIN BookEntity AS b ON b.id = b2r.bookId " +
            "WHERE b.id = :id")
    List<Book2RatingEntity> bookRatingByBookId(@Param("id") Integer id);

    @Query(value = "select round(avg(b2r.rating))\n" +
            "from book2rating b2r\n" +
            "join book b on b.id = b2r.book_id\n" +
            "where b.id = :id", nativeQuery = true)
    Integer averageRating(@Param("id") Integer id);

}
