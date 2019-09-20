package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @GetMapping(value = "/books",
            produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request)
    {
//        logger.trace(request.getMethod()
//                .toUpperCase() + " " + request.getRequestURI() + " accessed");
//
//        List<Book> allBooks = BookService.findAll();
//        return new ResponseEntity<>(allBooks, HttpStatus.OK);
        return null;
    }

}
