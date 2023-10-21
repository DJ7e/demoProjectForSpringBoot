package com.another.ann.dao;

import com.another.ann.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookDao {


    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findManyBooks();
}
