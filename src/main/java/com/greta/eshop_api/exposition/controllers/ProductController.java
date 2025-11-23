package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.persistence.repositories.ProductRepository;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products;
    }

    @GetMapping("/search")
    public List<ProductEntity> searchProducts(@RequestParam String keyword) {
        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCase(keyword);
        return products;
    }
}