package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.ProductDTO;
import com.greta.eshop_api.exposition.mappers.ProductMapper;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(HttpServletRequest request) {
        List<ProductDTO> dtos = productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();

        ApiResponse response = new ApiResponse(
                200,
                "Liste des produits récupérée avec succès",
                request.getRequestURI(),
                dtos
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        return productRepository.findById(id)
                .map(product -> {
                    ApiResponse resp = new ApiResponse(
                            200,
                            "Produit récupéré avec succès",
                            request.getRequestURI(),
                            ProductMapper.toDTO(product)
                    );
                    return ResponseEntity.ok(resp);
                })
                .orElseGet(() -> {
                    ApiResponse resp = new ApiResponse(
                            404,
                            "Produit introuvable",
                            request.getRequestURI(),
                            null
                    );
                    return ResponseEntity.status(404).body(resp);
                });
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            HttpServletRequest request
    ) {
        List<ProductEntity> products = productRepository.findAll();

        if (name != null) {
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (maxPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                    .toList();
        }

        if (category != null) {
            products = products.stream()
                    .filter(p -> p.getCategory() != null &&
                            p.getCategory().getName().equalsIgnoreCase(category))
                    .toList();
        }

        List<ProductDTO> dtos = products.stream()
                .map(ProductMapper::toDTO)
                .toList();

        ApiResponse response = new ApiResponse(
                200,
                "Résultat de la recherche",
                request.getRequestURI(),
                dtos
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(
            @RequestBody ProductEntity product,
            HttpServletRequest request
    ) {
        ProductEntity saved = productRepository.save(product);

        ApiResponse response = new ApiResponse(
                201,
                "Produit créé avec succès",
                request.getRequestURI(),
                ProductMapper.toDTO(saved)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductEntity newData,
            HttpServletRequest request
    ) {
        ProductEntity existing = productRepository.findById(id).orElse(null);

        if (existing == null) {
            ApiResponse resp = new ApiResponse(
                    404,
                    "Produit introuvable",
                    request.getRequestURI(),
                    null
            );
            return ResponseEntity.status(404).body(resp);
        }

        existing.setName(newData.getName());
        existing.setDescription(newData.getDescription());
        existing.setImageUrl(newData.getImageUrl());
        existing.setActive(newData.isActive());
        existing.setPrice(newData.getPrice());
        existing.setStockQuantity(newData.getStockQuantity());
        existing.setDiscount(newData.getDiscount());

        ProductEntity updated = productRepository.save(existing);

        ApiResponse resp = new ApiResponse(
                200,
                "Produit mis à jour avec succès",
                request.getRequestURI(),
                ProductMapper.toDTO(updated)
        );

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        if (!productRepository.existsById(id)) {
            ApiResponse resp = new ApiResponse(
                    404,
                    "Produit introuvable",
                    request.getRequestURI(),
                    null
            );
            return ResponseEntity.status(404).body(resp);
        }

        productRepository.deleteById(id);

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
        productRepository.deleteAll();

        ApiResponse resp = new ApiResponse(
                204,
                "Tous les produits ont été supprimés",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(204).body(resp);
    }
}
