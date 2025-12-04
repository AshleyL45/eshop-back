package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.ReviewService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Reviews.ReviewRequestDTO;
import com.greta.eshop_api.exposition.dtos.Reviews.ReviewResponseDTO;
import com.greta.eshop_api.exposition.mappers.ReviewMapper;
import com.greta.eshop_api.persistence.entities.ReviewEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> createReview(
            @Valid @RequestBody ReviewRequestDTO dto,
            HttpServletRequest http
    ) {
        ReviewEntity review = reviewService.createReview(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Review created",
                        http.getRequestURI(),
                        ReviewMapper.toResponseDTO(review)
                ));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getReviewsByProduct(
            @PathVariable Long productId,
            HttpServletRequest http
    ) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProduct(productId)
                .stream()
                .map(ReviewMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Reviews fetched",
                        http.getRequestURI(),
                        reviews
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequestDTO dto,
            HttpServletRequest http
    ) {
        ReviewEntity updated = reviewService.updateReview(id, dto);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Review updated",
                        http.getRequestURI(),
                        ReviewMapper.toResponseDTO(updated)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @PathVariable Long id,
            HttpServletRequest http
    ) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Review deleted",
                        http.getRequestURI(),
                        null
                )
        );
    }
}
