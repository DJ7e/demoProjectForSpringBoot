package com.another.ann.dao.impl;

import com.another.ann.TestDataUtil;
import com.another.ann.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl undertest;

    @Test
    public void testTheyShit(){
        Author author = TestDataUtil.createTestAuthorA();

        undertest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES (?,?,?) "),
                eq(1L),eq("HAGSY"),eq(20)
        );
    }

    @Test
    public void findTestOneGeneratesTheCorrectSql(){
        undertest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void findThatFindManyGeneratesTheCorrectSql() {
        undertest.findManyAuthors();
        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        undertest.update(author.getId(), author);
        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L,"HAGSY",20,1L
        );
    }
}
