package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.FavoriteService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteRequestDTO;
import com.greta.eshop_api.exposition.dtos.Favorite.FavoriteResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/favorites")
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FavoriteResponseDTO>>> getFavorites(
            @PathVariable Long customerId,
            HttpServletRequest http
    ) {
        List<FavoriteResponseDTO> data = service.getByCustomer(customerId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Favoris du client récupérés",
                        http.getRequestURI(),
                        data
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FavoriteResponseDTO>> addFavorite(
            @PathVariable Long customerId,
            @Valid @RequestBody FavoriteRequestDTO dto,
            HttpServletRequest http
    ) {
        FavoriteResponseDTO created = service.addFavorite(customerId, dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Favori ajouté",
                        http.getRequestURI(),
                        created
                ));
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<ApiResponse<Void>> deleteFavorite(
            @PathVariable Long customerId,
            @PathVariable Long favoriteId,
            HttpServletRequest http
    ) {
        service.deleteFavorite(customerId, favoriteId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Favori supprimé",
                        http.getRequestURI(),
                        null
                )
        );
    }
}
