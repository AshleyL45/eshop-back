package com.greta.eshop_api.exposition.dtos.Products;

public record ProductResponseDTO(
        Long id,
        String name,
        String scientificName,
        String description,
        String longDescription,
        double price,
        String imageUrl,
        int stockQuantity,
        double rating,
        boolean active,
        double discount,
        String expertAdvice,
        Long categoryId,
        String categoryName
) {}

