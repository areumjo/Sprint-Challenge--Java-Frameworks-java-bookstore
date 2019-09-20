package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Override
    public List<Book> findAll(Pageable pageable) {
//        List<Book> bookList = new ArrayList<>();
//        bookrepos.findAll((org.springframework.data.domain.Pageable) pageable).iterator().forEachRemaining(bookList::add);
//        return bookList;
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
            {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Book findBookById(long id) {
//        bookrepos.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
//        return bookrepos;
        return null;
    }

    @Transactional
    @Override
    public Book save(Book book) {
//        Book newBook = new Book();
//        newBook.setBooktitle(Book.getBooktitle());
//        return bookrepos.save(newBook);
        return null;
    }

    @Override
    public Book update(Book book, long id) {
//        Book currentBook = bookrepos.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
//        if (book.getBooktitle() != null)
//        {
//            currentBook.setBooktitle(book.getBooktitle());
//        }
//        return bookrepos.save(currentBook);
        return null;
    }
}
