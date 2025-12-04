package com.greta.eshop_api.exposition.dtos.CartItem;

public record CartItemRequestDTO(
        Long productId,
        int quantity
) {}
