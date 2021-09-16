package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByPubDateBetween(LocalDate pubDateFrom, LocalDate pubDateTo, Pageable pageable);

    //    @Query("from BookEntity as b join Book2TagEntity as b2t on b.id = b2t.bookId where b2t.tagId = :id")
    @Query(value = "select  b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, b.rating\n" +
            "from book b\n" +
            "join book2tag b2t on b.id = b2t.book_id\n" +
            "where b2t.tag_id = :id", nativeQuery = true)
    Page<BookEntity> findBookEntitiesByTagId(@Param("id") Integer id, Pageable pageable);

    @Query("select t.name from TagEntity as t where t.id = :id")
    String tagNameByTagId(@Param("id") Integer id);
}
