package com.greta.eshop_api.exposition.dtos.Products;

import com.greta.eshop_api.exposition.dtos.BotanicalInfo.BotanicalInfoResponseDTO;
import com.greta.eshop_api.exposition.dtos.CareInfo.CareInfoResponseDTO;

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
        String categoryName,

        BotanicalInfoResponseDTO botanicalInfo,
        CareInfoResponseDTO careInfo) {}
