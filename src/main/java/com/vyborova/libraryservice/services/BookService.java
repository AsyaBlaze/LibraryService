package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.Book;
import com.vyborova.libraryservice.models.BookInfo;
import com.vyborova.libraryservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void update(UUID id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    public List<BookInfo> allBookInfo() {
         return bookRepository.findAllBooksInfo();
    }

    public void deleteById(UUID uuid) {
        bookRepository.deleteById(uuid);
    }
}
