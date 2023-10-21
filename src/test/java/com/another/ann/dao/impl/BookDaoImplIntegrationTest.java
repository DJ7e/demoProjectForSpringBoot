package com.another.ann.dao.impl;

import com.another.ann.TestDataUtil;
import com.another.ann.domain.Author;
import com.another.ann.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private AuthorDaoImpl authorDao;
    private BookDaoImpl underTest;
    @Autowired
    public void setUnderTest(BookDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Autowired
    public BookDaoImplIntegrationTest(AuthorDaoImpl authorDao) {
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
//        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBManyBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        authorDao.create(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        authorDao.create(authorC);

        Book bookA = TestDataUtil.createTestBookA();
        underTest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        underTest.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        underTest.create(bookC);

        List<Book> result = underTest.findManyBooks();
        assertThat(result).hasSize(3).containsExactly(bookA,bookB,bookC);

    }

}
