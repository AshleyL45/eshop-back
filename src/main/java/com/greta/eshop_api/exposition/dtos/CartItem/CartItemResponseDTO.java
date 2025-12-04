package com.greta.eshop_api.exposition.dtos.CartItem;

public record CartItemResponseDTO(
        Long id,
        int quantity,
        Long productId,
        String productName
) {}
