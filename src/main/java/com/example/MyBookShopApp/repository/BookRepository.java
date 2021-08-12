package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT book_id, title, priceold, price, full_name FROM books b JOIN author a ON a.author_id=b.book_id", (ResultSet rs, int rowNum) -> {
                    Book book = new Book();
                    book.setId(rs.getInt("book_id"));
                    book.setAuthor(rs.getString("full_name"));
                    book.setTitle(rs.getString("title"));
                    book.setPriceOld(rs.getString("priceold"));
                    book.setPrice(rs.getString("price"));
                    return book;
                });
        return new ArrayList<>(books);
    }

}
