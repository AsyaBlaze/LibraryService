package com.vyborova.libraryservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.NumberFormat;

import java.util.UUID;

@Table(name = "genres_statistic")
@Immutable
@Entity
@Getter
public class GenreStatisticView {
    @Id
    private UUID id;
    private String name;
    private Long book_count;
    private Double average_rating;
}
