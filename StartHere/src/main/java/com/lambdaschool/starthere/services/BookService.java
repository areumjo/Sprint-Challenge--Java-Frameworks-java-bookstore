package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

    void delete(long id);

    Book findBookById(long id);

    Book save (Book book);

    Book update(Book book, long id);
}
