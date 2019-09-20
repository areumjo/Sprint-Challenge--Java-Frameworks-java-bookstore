package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    AuthorRepository authorepos;

    @Override
    public ArrayList<Author> findAll() {
        ArrayList<Author> authorList = new ArrayList<>();
        authorepos.findAll().iterator().forEachRemaining(authorList::add);
        return authorList;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Author findBookById(long id) {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Author update(Author author, long id) {
        return null;
    }
}
