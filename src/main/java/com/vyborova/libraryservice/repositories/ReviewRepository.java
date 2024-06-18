package com.vyborova.libraryservice.repositories;

import com.vyborova.libraryservice.models.BookReviewInfo;
import com.vyborova.libraryservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    public List<Review> findAllByBookId(UUID uuid);

    @Query("SELECT new com.vyborova.libraryservice.models.BookReviewInfo(b.title, AVG(r.rating), COUNT(r)) " +
            "FROM Review r " +
            "JOIN r.book b " +
            "GROUP BY b.title")
    List<BookReviewInfo> getReviewsInfo();
}
