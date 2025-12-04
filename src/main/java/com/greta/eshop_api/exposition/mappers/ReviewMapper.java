package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Reviews.ReviewResponseDTO;
import com.greta.eshop_api.persistence.entities.ReviewEntity;

public class ReviewMapper {

    public static ReviewResponseDTO toResponseDTO(ReviewEntity entity) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setReviewDate(entity.getReviewDate());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setProductId(entity.getProduct().getId());
        return dto;
    }
}
