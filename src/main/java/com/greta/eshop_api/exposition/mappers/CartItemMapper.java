package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.CartItem.CartItemResponseDTO;
import com.greta.eshop_api.persistence.entities.CartItemEntity;

public class CartItemMapper {

    public static CartItemResponseDTO toResponseDTO(CartItemEntity entity) {
        return new CartItemResponseDTO(
                entity.getId(),
                entity.getQuantity(),
                entity.getProduct().getId(),
                entity.getProduct().getName()
        );
    }
}
