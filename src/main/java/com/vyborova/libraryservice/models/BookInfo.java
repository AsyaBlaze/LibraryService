package com.vyborova.libraryservice.models;

import java.time.LocalDate;

public record BookInfo(String title, String authorName, String genre, LocalDate publishedDate) {
}
