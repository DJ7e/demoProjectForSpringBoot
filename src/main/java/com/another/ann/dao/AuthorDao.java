package com.another.ann.dao;

import com.another.ann.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorDao {

    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> findManyAuthors();

    void update(Long id,Author author);

}
