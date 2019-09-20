package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @GetMapping(value = "/authors",
            produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(HttpServletRequest request)
    {
        List<Author> allAuthors = authorService.findAll();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }
}
