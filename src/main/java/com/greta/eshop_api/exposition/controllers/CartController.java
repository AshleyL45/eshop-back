package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.CartService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;


import com.greta.eshop_api.exposition.dtos.Cart.CartRequestDTO;
import com.greta.eshop_api.exposition.dtos.Cart.CartResponseDTO;
import com.greta.eshop_api.exposition.dtos.CartItem.CartItemRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CartResponseDTO>> createCart(
            @RequestBody CartRequestDTO request,
            HttpServletRequest http
    ) {
        CartResponseDTO cart = cartService.createCart(request);

        ApiResponse<CartResponseDTO> response = new ApiResponse<>(
                201,
                "Cart créé avec succès",
                http.getRequestURI(),
                cart
        );

        return ResponseEntity.status(201).body(response);
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> getCartByCustomer(
            @PathVariable Long customerId,
            HttpServletRequest http
    ) {
        CartResponseDTO cart = cartService.getCartByCustomerId(customerId);

        ApiResponse<CartResponseDTO> response = new ApiResponse<>(
                200,
                "Cart récupéré",
                http.getRequestURI(),
                cart
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> updateItem(
            @PathVariable Long itemId,
            @RequestBody CartItemRequestDTO request,
            HttpServletRequest http
    ) {
        CartResponseDTO dto = cartService.updateItem(itemId, request);
        return ApiResponse.ok(dto);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(
            @PathVariable Long itemId,
            HttpServletRequest http
    ) {
        cartService.deleteItem(itemId);
        return ApiResponse.ok("Item supprimé", http.getRequestURI(), null);
    }


}
