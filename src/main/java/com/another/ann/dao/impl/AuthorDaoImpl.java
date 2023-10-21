package com.another.ann.dao.impl;

import com.another.ann.dao.AuthorDao;
import com.another.ann.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorDaoImpl implements AuthorDao {


    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                ("INSERT INTO authors (id,name,age) VALUES (?,?,?) "),
                author.getId(),author.getName(),author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(long authorId) {
//        return Optional<jdbcTemplate.query()>;
        List<Author> result  = jdbcTemplate.query(
                "SELECT id,name,age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                authorId
                );
        return result.stream().findFirst();
    }

    @Override
    public List<Author> findManyAuthors() {
        List<Author> result  = jdbcTemplate.query("SELECT id,name,age FROM authors",new AuthorRowMapper());
        return result;
    }

    @Override
    public void update(Long id, Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                author.getId(), author.getName(), author.getAge(), id
        );
    }


    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

}
