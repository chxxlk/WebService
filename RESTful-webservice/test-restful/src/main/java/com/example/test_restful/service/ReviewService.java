package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Review;
import com.example.test_restful.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() { return reviewRepository.findAll(); }
    public Optional<Review> getReviewById(Long id) { return reviewRepository.findById(id); }

    @Transactional
    public Review createReview(Review review) { return reviewRepository.save(review); }

    public Review updateReview(Review review) { return reviewRepository.save(review); }

    public void deleteReview(Long id) { reviewRepository.deleteById(id); }
}