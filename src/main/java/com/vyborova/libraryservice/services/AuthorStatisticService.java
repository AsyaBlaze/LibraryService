package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.AuthorStatisticView;
import com.vyborova.libraryservice.repositories.AuthorStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorStatisticService {
    private final AuthorStatisticRepository authorStatisticRepository;

    @Autowired
    public AuthorStatisticService(AuthorStatisticRepository authorStatisticRepository) {
        this.authorStatisticRepository = authorStatisticRepository;
    }

    public List<AuthorStatisticView> findAll() {
        return authorStatisticRepository.findAll();
    }
}
