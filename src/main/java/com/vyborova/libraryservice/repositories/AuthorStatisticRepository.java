package com.vyborova.libraryservice.repositories;

import com.vyborova.libraryservice.models.AuthorStatisticView;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorStatisticRepository extends ReadOnlyRepository<AuthorStatisticView, UUID> {

}
