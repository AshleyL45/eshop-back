package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Products.ProductRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductResponseDTO;
import com.greta.eshop_api.exposition.mappers.ProductMapper;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.CategoryRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(HttpServletRequest request) {

        List<ProductResponseDTO> dtos = productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDTO)
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
                            ProductMapper.toResponseDTO(product)
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

        List<ProductResponseDTO> dtos = products.stream()
                .map(ProductMapper::toResponseDTO)
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
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        CategoryEntity category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId()).orElse(null);
        }

        ProductEntity entity = ProductMapper.toEntity(request, category);
        ProductEntity saved = productRepository.save(entity);

        ApiResponse response = new ApiResponse(
                201,
                "Produit créé avec succès",
                httpRequest.getRequestURI(),
                ProductMapper.toResponseDTO(saved)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request,
            HttpServletRequest httpRequest
    ) {
        ProductEntity existing = productRepository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse(404, "Produit introuvable", httpRequest.getRequestURI(), null)
            );
        }

        CategoryEntity category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId()).orElse(null);
        }

        existing.setName(request.name());
        existing.setScientificName(request.scientificName());
        existing.setDescription(request.description());
        existing.setLongDescription(request.longDescription());
        existing.setPrice(request.price());
        existing.setImageUrl(request.imageUrl());
        existing.setStockQuantity(request.stockQuantity());
        existing.setRating(request.rating());
        existing.setActive(request.active());
        existing.setDiscount(request.discount());
        existing.setExpertAdvice(request.expertAdvice());
        existing.setCategory(category);

        ProductEntity updated = productRepository.save(existing);

        ApiResponse resp = new ApiResponse(
                200,
                "Produit mis à jour avec succès",
                httpRequest.getRequestURI(),
                ProductMapper.toResponseDTO(updated)
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
