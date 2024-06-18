package com.vyborova.libraryservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "published_date", columnDefinition = "DATE")
    private LocalDate publishedDate;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "isbn")
    private String isbn;

    @Min(value = 20)
    @Column(name = "price", columnDefinition = "NUMERIC")
    private Integer price;

    public Book() {
        this.author = new Author();
        this.genre = new Genre();
    }
}
