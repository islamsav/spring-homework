package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByPubDateBetween(LocalDate pubDateFrom, LocalDate pubDateTo, Pageable pageable);

    @Query(value = "select b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title, count(*) as times_bought\n" +
            "from book b\n" +
            "join book2user b2u on b.id = b2u.book_id\n" +
            "group by b.id, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b.title\n" +
            "order by times_bought desc", nativeQuery = true)
    List<BookEntity> booksToUser();

//    B — количество пользователей, купивших книгу,
//    C — количество пользователей, у которых книга находится в корзине,
//    K — количество пользователей, у которых книга отложена.
}
