package org.example.app.repository;

import org.example.web.dto.BookToRemove;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    int removeBook(BookToRemove bookToRemove);

    List<T> filter(T book);
}
