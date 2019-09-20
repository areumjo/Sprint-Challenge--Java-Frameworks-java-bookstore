package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.awt.print.Pageable;
import java.util.ArrayList;

public interface BookService
{
    ArrayList<Book> findAll();

    void delete(long id);

    Book findBookById(long id);

    Book save (Book book);

    Book update(Book book, long id);
}
