package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.exposition.dtos.Reviews.ReviewRequestDTO;
import com.greta.eshop_api.exposition.mappers.ReviewMapper;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.entities.ReviewEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;
import com.greta.eshop_api.persistence.repositories.ReviewRepository;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public ReviewEntity createReview(ReviewRequestDTO dto) {

        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + dto.getCustomerId() + " not found"));

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + dto.getProductId() + " not found"));

        ReviewEntity review = new ReviewEntity();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReviewDate(LocalDateTime.now());
        review.setCustomer(customer);
        review.setProduct(product);

        return reviewRepository.save(review);
    }


    public List<ReviewEntity> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public ReviewEntity updateReview(Long id, ReviewRequestDTO dto) {

        ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with ID " + id + " not found"));

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }


    public void deleteReview(Long id) {
        ReviewEntity review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with ID " + id + " not found"));

        reviewRepository.delete(review);
    }
}
