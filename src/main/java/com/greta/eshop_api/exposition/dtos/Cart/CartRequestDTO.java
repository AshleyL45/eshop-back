package com.greta.eshop_api.exposition.dtos.Cart;

import com.greta.eshop_api.exposition.dtos.CartItem.CartItemRequestDTO;

import java.util.List;

public record CartRequestDTO(
        Long customerId,
        List<CartItemRequestDTO> items
) {}
