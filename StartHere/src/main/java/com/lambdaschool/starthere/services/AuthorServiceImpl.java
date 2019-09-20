package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    AuthorRepository authorepos;

    @Override
    public List<Author> findAll(Pageable pageable) {
        List<Author> authorList = new ArrayList<>();
        authorepos.findAll(pageable).iterator().forEachRemaining(authorList::add);
        return authorList;
    }

    @Override
    public void delete(long id) {
        if (authorepos.findById(id).isPresent())
        {
            authorepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Author findAuthorById(long id) {
        return authorepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with author id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public Author save(Author author) {
        Author newAuthor = new Author();
        newAuthor.setFirstname(author.getFirstname());
        newAuthor.setLastname(author.getLastname());
        return authorepos.save(newAuthor);
    }

    @Override
    public Author update(Author author, long id) {
        Author currentCourse = authorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (author.getFirstname() != null)
        {
            currentCourse.setFirstname(author.getFirstname());
        }
        if (author.getLastname() != null)
        {
            currentCourse.setLastname(author.getLastname());
        }
        return authorepos.save(currentCourse);
    }
}
