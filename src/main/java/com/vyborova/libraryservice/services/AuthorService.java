package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.Author;
import com.vyborova.libraryservice.models.Book;
import com.vyborova.libraryservice.repositories.AuthorRepository;
import com.vyborova.libraryservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void update(UUID id, Author author) {
        author.setId(id);
        authorRepository.save(author);
    }
}
