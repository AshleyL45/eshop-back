package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.CartService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Cart.CartRequestDTO;
import com.greta.eshop_api.exposition.dtos.Cart.CartResponseDTO;
import com.greta.eshop_api.exposition.dtos.CartItem.CartItemRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
            @Valid @RequestBody CartRequestDTO request,
            HttpServletRequest http
    ) {
        CartResponseDTO cart = cartService.createCart(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Cart créé avec succès",
                        http.getRequestURI(),
                        cart
                ));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> getCartByCustomer(
            @PathVariable Long customerId,
            HttpServletRequest http
    ) {
        CartResponseDTO cart = cartService.getCartByCustomerId(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Cart récupéré",
                        http.getRequestURI(),
                        cart
                )
        );
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> updateItem(
            @PathVariable Long itemId,
            @Valid @RequestBody CartItemRequestDTO request,
            HttpServletRequest http
    ) {
        CartResponseDTO updated = cartService.updateItem(itemId, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Item mis à jour",
                        http.getRequestURI(),
                        updated
                )
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(
            @PathVariable Long itemId,
            HttpServletRequest http
    ) {
        cartService.deleteItem(itemId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Item supprimé",
                        http.getRequestURI(),
                        null
                )
        );
    }
}
