package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface AuthorService
{
    List<Author> findAll();

    void delete(long id);

    Author findAuthorById(long id);

    Author save(Author author);

    Author update(Author author, long id);
}
