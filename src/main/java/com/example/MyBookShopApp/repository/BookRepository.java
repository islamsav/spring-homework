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

    @Query(value = "select  b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, b.rating\n" +
            "from book b\n" +
            "join book2tag b2t on b.id = b2t.book_id\n" +
            "where b2t.tag_id = :id", nativeQuery = true)
    Page<BookEntity> findBookEntitiesByTagId(@Param("id") Integer id, Pageable pageable);

    @Query("select t.name from TagEntity as t where t.id = :id")
    String tagNameByTagId(@Param("id") Integer id);

    @Query("FROM BookEntity AS b ORDER BY b.rating DESC")
    Page<BookEntity> findAllBooksByHighRating(Pageable pageable);

    @Query("FROM BookEntity AS b ORDER BY b.pubDate DESC")
    Page<BookEntity> findAllBooksByPubDate(Pageable pageable);

    @Query(value = "select b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, b.rating\n" +
            "from book b\n" +
            "join book2genre b2g on b2g.book_id = b.id\n" +
            "join genre g on b2g.genre_id = g.id\n" +
            "where g.id = :id", nativeQuery = true)
    Page<BookEntity> findBooksToGenreId(Integer id, Pageable pageable);

    @Query(value = "select b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, b.rating\n" +
            "from book b\n" +
            "join book2genre b2g on b2g.book_id = b.id\n" +
            "join genre g on b2g.genre_id = g.id\n" +
            "where g.id = :id", nativeQuery = true)
    List<BookEntity> findBooksByGenreId(Integer id);

    @Query(value = "select b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, b.rating\n" +
            " from book b\n" +
            " join book2author b2a on b.id = b2a.book_id\n" +
            " join author a on a.id = b2a.author_id\n" +
            " where a.id = :id", nativeQuery = true)
    Page<BookEntity> findAuthorEntityById(Integer id, Pageable pageable);

    BookEntity findBookEntityBySlug(String slug);
}
