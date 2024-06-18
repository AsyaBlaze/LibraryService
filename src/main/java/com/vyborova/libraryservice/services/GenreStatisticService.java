package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.GenreStatisticView;
import com.vyborova.libraryservice.repositories.GenreStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreStatisticService {
    private final GenreStatisticRepository genreStatisticRepository;

    @Autowired
    public GenreStatisticService(GenreStatisticRepository genreStatisticRepository) {
        this.genreStatisticRepository = genreStatisticRepository;
    }

    public List<GenreStatisticView> findAll() {
        return genreStatisticRepository.findAll();
    }
}
