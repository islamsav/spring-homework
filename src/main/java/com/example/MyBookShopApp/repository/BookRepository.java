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

    @Query(value = "select b.id, b.title, b.description, b.discount, b.image, b.is_bestseller, b.price, b.pub_date, b.slug, b2ut.name \n" +
            "from book as b \n" +
            "join book2user b2u on b.id = b2u.book_id \n" +
            "    join book2user_type b2ut on b2u.type_id = b2ut.id", nativeQuery = true)
    List<BookEntity> booksToUser();



//    B — количество пользователей, купивших книгу,
//    C — количество пользователей, у которых книга находится в корзине,
//    K — количество пользователей, у которых книга отложена.
}
