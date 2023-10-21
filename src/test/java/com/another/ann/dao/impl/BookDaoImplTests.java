package com.another.ann.dao.impl;

import com.another.ann.TestDataUtil;
import com.another.ann.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl undertest;

    @Test
    public void testTheyShitpart2() {

        Book book = TestDataUtil.createTestBookA();

        undertest.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)"),
                eq("123456"), eq("DJBravo"), eq(1L)
        );
    }

    @Test
    public void findTestOneGeneratesTheCorrectSql() {
        undertest.findOne("123456");
        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("123456")
        );
    }

    @Test
    public void findThatFindManyGeneratesTheCorrectSql() {
        undertest.findManyBooks();
        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }
}
