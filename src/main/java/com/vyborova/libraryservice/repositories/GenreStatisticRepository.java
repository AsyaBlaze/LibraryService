package com.vyborova.libraryservice.repositories;

import com.vyborova.libraryservice.models.GenreStatisticView;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreStatisticRepository extends ReadOnlyRepository<GenreStatisticView, UUID> {

}
