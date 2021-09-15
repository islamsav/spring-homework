package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByPubDateBetween(LocalDate pubDateFrom, LocalDate pubDateTo, Pageable pageable);

    @Query(value =
            "SELECT count(*)  as count,\n" +
                    "       b.id,\n" +
                    "       b.slug,\n" +
                    "       b.pub_date,\n" +
                    "       b.price,\n" +
                    "       b.is_bestseller,\n" +
                    "       b.image,\n" +
                    "       b.discount,\n" +
                    "       b.description,\n" +
                    "       b.title,\n" +
                    "       b2ut.name as status\n" +
                    "FROM book as b\n" +
                    "         JOIN book2user b2u on b.id = b2u.book_id\n" +
                    "         JOIN book2user_type b2ut on b2u.type_id = b2ut.id\n" +
                    "group by b.id, b2ut.name, b2u.book_id\n" +
                    "order by count desc", nativeQuery = true)
    Page<BookEntity> booksByStatusCounts(Pageable pageable);

}
