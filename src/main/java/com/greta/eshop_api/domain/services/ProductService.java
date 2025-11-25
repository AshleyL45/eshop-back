package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.rules.ProductRules;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.exposition.dtos.Products.ProductRequestDTO;
import com.greta.eshop_api.exposition.dtos.Products.ProductResponseDTO;
import com.greta.eshop_api.exposition.mappers.ProductMapper;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.CategoryRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // GET ALL
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    // GET BY ID
    public ProductResponseDTO findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));
        return ProductMapper.toResponseDTO(entity);
    }

    // SEARCH
    public List<ProductResponseDTO> search(String name, Double maxPrice, String category) {
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

        return products.stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    // CREATE
    public ProductResponseDTO create(ProductRequestDTO request) {

        CategoryEntity category = null;

        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElse(null);
        }

        ProductEntity entity = ProductMapper.toEntity(request, category);

        ProductRules.validateBeforeCreation(entity);

        ProductEntity saved = productRepository.save(entity);
        return ProductMapper.toResponseDTO(saved);
    }

    // UPDATE
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

        CategoryEntity category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElse(null);
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

        ProductRules.validateBeforeUpdate(existing);

        ProductEntity saved = productRepository.save(existing);
        return ProductMapper.toResponseDTO(saved);
    }

    // DELETE
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit introuvable");
        }
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}
