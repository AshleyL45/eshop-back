package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Cart.CartResponseDTO;
import com.greta.eshop_api.persistence.entities.CartEntity;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartResponseDTO toResponseDTO(CartEntity entity) {
        return new CartResponseDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getItems().stream()
                        .map(CartItemMapper::toResponseDTO)
                        .collect(Collectors.toList())
        );
    }
}
