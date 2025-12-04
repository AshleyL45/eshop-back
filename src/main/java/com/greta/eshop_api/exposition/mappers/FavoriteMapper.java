package com.greta.eshop_api.exposition.mappers;


import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteRequestDTO;
import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteResponseDTO;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.FavoriteEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;

public class FavoriteMapper {

    public static FavoriteResponseDTO toResponseDTO(FavoriteEntity entity) {
        FavoriteResponseDTO dto = new FavoriteResponseDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setProductId(entity.getProduct().getId());
        return dto;
    }

    public static FavoriteEntity toEntity(
            FavoriteRequestDTO dto,
            CustomerEntity customer,
            ProductEntity product
    ) {
        FavoriteEntity entity = new FavoriteEntity();
        entity.setCustomer(customer);
        entity.setProduct(product);
        return entity;
    }
}
