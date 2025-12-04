package com.greta.eshop_api.exposition.dtos.Cart;

import com.greta.eshop_api.exposition.dtos.CartItem.CartItemResponseDTO;

import java.util.List;

public record CartResponseDTO(
        Long id,
        Long customerId,
        List<CartItemResponseDTO> items
) {}
