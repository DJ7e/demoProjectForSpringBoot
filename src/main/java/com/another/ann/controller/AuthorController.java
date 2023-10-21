package com.another.ann.controller;

import com.another.ann.dao.AuthorDao;
import com.another.ann.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
