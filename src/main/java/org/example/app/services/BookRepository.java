package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll())
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book by id: " + bookIdToRemove);
                return repo.remove(book);
            }
        return false;
    }

    @Override
    public void removeItemByParam(Book item) {
        retreiveAll().forEach(book -> {
            if (book.getId().equals(item.getId()) ||
                    book.getAuthor().equals(item.getAuthor()) ||
                    book.getTitle().equals(item.getTitle()) ||
                    book.getSize().equals(item.getSize())) {
                logger.info("remove book by id: " + book.getId());
                repo.remove(book);
            }
        });
    }
}