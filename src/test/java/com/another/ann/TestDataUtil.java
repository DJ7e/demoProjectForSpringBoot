package com.another.ann;

import com.another.ann.domain.Author;
import com.another.ann.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("HAGSY")
                .age(20)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("HAGSY2")
                .age(22)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("HAGSY3")
                .age(24)
                .build();
    }
    public static Book createTestBookA() {
        return Book.builder()
                .isbn("123456")
                .title("DJBravo")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("123457")
                .title("DJBravoBig")
                .authorId(2L)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("123458")
                .title("DJBravoBigger")
                .authorId(3L)
                .build();
    }
}
