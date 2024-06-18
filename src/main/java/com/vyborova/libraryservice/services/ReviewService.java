package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.Book;
import com.vyborova.libraryservice.models.BookReviewInfo;
import com.vyborova.libraryservice.models.Review;
import com.vyborova.libraryservice.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public List<BookReviewInfo> getReviewInfo() {
        return reviewRepository.getReviewsInfo();
    }

    public List<Review> findAllByBookId(UUID id) {
        return reviewRepository.findAllByBookId(id);
    }
}
