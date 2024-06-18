package com.vyborova.libraryservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import java.util.UUID;

@Table(name = "author_statistic")
@Immutable
@Entity
@Getter
public class AuthorStatisticView {
    @Id
    private UUID id;
    private String name;
    private Long book_count;
    private Double average_rating;
}
