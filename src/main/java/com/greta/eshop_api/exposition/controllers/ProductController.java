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
    public ResponseEntity<ApiResponse> getAllProducts(HttpServletRequest request) {

        List<ProductResponseDTO> dtos = productService.findAll();

        ApiResponse resp = new ApiResponse(
                200,
                "Liste des produits récupérée avec succès",
                request.getRequestURI(),
                dtos
        );

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        ProductResponseDTO dto = productService.findById(id);

        ApiResponse resp = new ApiResponse(
                200,
                "Produit récupéré avec succès",
                request.getRequestURI(),
                dto
        );

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            HttpServletRequest request
    ) {

        List<ProductResponseDTO> dtos = productService.search(name, maxPrice, category);

        ApiResponse resp = new ApiResponse(
                200,
                "Résultat de la recherche",
                request.getRequestURI(),
                dtos
        );

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        ProductResponseDTO dto = productService.create(request);

        ApiResponse resp = new ApiResponse(
                201,
                "Produit créé avec succès",
                httpRequest.getRequestURI(),
                dto
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        ProductResponseDTO dto = productService.update(id, request);

        ApiResponse resp = new ApiResponse(
                200,
                "Produit mis à jour avec succès",
                httpRequest.getRequestURI(),
                dto
        );

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        productService.delete(id);

        ApiResponse resp = new ApiResponse(
                204,
                "Produit supprimé avec succès",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(204).body(resp);
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse> deleteAllProducts(HttpServletRequest request) {

        productService.deleteAll();

        ApiResponse resp = new ApiResponse(
                204,
                "Tous les produits ont été supprimés",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(204).body(resp);
    }
}
