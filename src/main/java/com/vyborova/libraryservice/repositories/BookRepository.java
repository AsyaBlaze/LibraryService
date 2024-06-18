package com.vyborova.libraryservice.repositories;

import com.vyborova.libraryservice.models.Book;
import com.vyborova.libraryservice.models.BookInfo;
import com.vyborova.libraryservice.models.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT new com.vyborova.libraryservice.models.BookInfo(b.title, a.name, g.name, b.publishedDate) " +
            "FROM Book b " +
            "JOIN b.author a " +
            "JOIN b.genre g")
    List<BookInfo> findAllBooksInfo();
}
