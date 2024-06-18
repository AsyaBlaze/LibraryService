package com.vyborova.libraryservice.models;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class BookReviewInfo {
    private final String title;
    private final String averageRating;
    private final Long reviews;

    public BookReviewInfo(String title, Double averageRating, Long reviews) {
        this.title = title;
        this.averageRating = new DecimalFormat("#.##").format(averageRating);
        this.reviews = reviews;
    }
}
