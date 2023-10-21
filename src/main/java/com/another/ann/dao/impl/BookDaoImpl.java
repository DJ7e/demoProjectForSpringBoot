package com.another.ann.dao.impl;

import com.another.ann.dao.BookDao;
import com.another.ann.domain.Author;
import com.another.ann.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    Unit Test for Inserting a Tuple
    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );
    }

//    Unit Test for Finding a Tuple
    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> result = jdbcTemplate.query(
                "SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Book> findManyBooks() {
        List<Book> result = jdbcTemplate.query(
                "SELECT isbn,title,author_id FROM books",
                new BookRowMapper()
        );
        return result;
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }


}
