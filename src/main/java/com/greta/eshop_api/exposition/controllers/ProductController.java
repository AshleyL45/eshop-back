package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.ProductService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Products.ProductRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAllProducts(HttpServletRequest request) {
        List<ProductResponseDTO> dtos = productService.findAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Liste des produits récupérée avec succès",
                        request.getRequestURI(),
                        dtos
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        ProductResponseDTO dto = productService.findById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Produit récupéré avec succès",
                        request.getRequestURI(),
                        dto
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            HttpServletRequest request
    ) {
        List<ProductResponseDTO> dtos = productService.search(name, maxPrice, category);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Résultat de la recherche",
                        request.getRequestURI(),
                        dtos
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        ProductResponseDTO dto = productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Produit créé avec succès",
                        httpRequest.getRequestURI(),
                        dto
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        ProductResponseDTO dto = productService.update(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Produit mis à jour avec succès",
                        httpRequest.getRequestURI(),
                        dto
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        productService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Produit supprimé avec succès",
                        request.getRequestURI(),
                        null
                )
        );
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse<Void>> deleteAllProducts(HttpServletRequest request) {
        productService.deleteAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Tous les produits ont été supprimés",
                        request.getRequestURI(),
                        null
                )
        );
    }
}
