package com.another.ann.controller;

import com.another.ann.dao.AuthorDao;
import com.another.ann.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")

public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping(path = "get")
    public List<Author> getAuthors(){
        List<Author> result = authorDao.findManyAuthors();
        return result;
    }

    @PostMapping(path = "add/")
    public String addAuthor(@RequestBody Author author){
        Author obj = Author.builder()
                .age(author.getAge())
                .id(author.getId())
                .name(author.getName())
                .build();
        authorDao.create(obj);
        return "Done";
    }

}
