package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.Genre;
import com.vyborova.libraryservice.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(UUID id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void update(UUID id, Genre genre) {
        genre.setId(id);
        genreRepository.save(genre);
    }
}
