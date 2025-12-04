package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Categories.CategoryRequestDTO;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryResponseDTO;
import com.greta.eshop_api.persistence.entities.CategoryEntity;

public class CategoryMapper {

    public static CategoryEntity toEntity(CategoryRequestDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.name());
        return entity;
    }

    public static CategoryResponseDTO toDto(CategoryEntity entity) {
        return new CategoryResponseDTO(
                entity.getId(),
                entity.getName()
        );
    }
}
