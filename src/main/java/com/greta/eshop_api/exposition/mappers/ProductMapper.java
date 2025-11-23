package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.ProductDTO;
import com.greta.eshop_api.persistence.entities.ProductEntity;

public class ProductMapper {

    public static ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) return null;

        ProductDTO dto = new ProductDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setScientificName(entity.getScientificName());
        dto.setDescription(entity.getDescription());
        dto.setLongDescription(entity.getLongDescription());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStockQuantity(entity.getStockQuantity());
        dto.setRating(entity.getRating());
        dto.setActive(entity.isActive());
        dto.setDiscount(entity.getDiscount());
        dto.setExpertAdvice(entity.getExpertAdvice());

        if (entity.getCategory() != null) {
            dto.setCategoryId(entity.getCategory().getId());
            dto.setCategoryName(entity.getCategory().getName());
        }

        return dto;
    }
}
