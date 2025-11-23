package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Products.ProductRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductResponseDTO;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequestDTO dto, CategoryEntity category) {

        ProductEntity entity = new ProductEntity();

        entity.setName(dto.name());
        entity.setScientificName(dto.scientificName());
        entity.setDescription(dto.description());
        entity.setLongDescription(dto.longDescription());
        entity.setPrice(dto.price());
        entity.setImageUrl(dto.imageUrl());
        entity.setStockQuantity(dto.stockQuantity());
        entity.setRating(dto.rating());
        entity.setActive(dto.active());
        entity.setDiscount(dto.discount());
        entity.setExpertAdvice(dto.expertAdvice());
        entity.setCategory(category);

        return entity;
    }

    public static ProductResponseDTO toResponseDTO(ProductEntity entity) {
        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getScientificName(),
                entity.getDescription(),
                entity.getLongDescription(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.getStockQuantity(),
                entity.getRating(),
                entity.isActive(),
                entity.getDiscount(),
                entity.getExpertAdvice(),
                entity.getCategory() != null ? entity.getCategory().getId() : null,
                entity.getCategory() != null ? entity.getCategory().getName() : null
        );
    }
}

