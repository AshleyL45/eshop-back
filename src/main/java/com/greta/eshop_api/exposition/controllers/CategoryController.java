package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryRequestDTO;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryResponseDTO;
import com.greta.eshop_api.exposition.mappers.CategoryMapper;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.repositories.CategoryRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity<ApiResponse> getAll(HttpServletRequest req) {
        List<CategoryResponseDTO> dtos = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse(200, "Liste des catégories", req.getRequestURI(), dtos)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id, HttpServletRequest req) {
        return categoryRepository.findById(id)
                .map(c -> ResponseEntity.ok(
                        new ApiResponse(200, "Catégorie trouvée", req.getRequestURI(), CategoryMapper.toDto(c))
                ))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        new ApiResponse(404, "Catégorie introuvable", req.getRequestURI(), null)
                ));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody CategoryRequestDTO dto,
            HttpServletRequest req
    ) {
        CategoryEntity saved = categoryRepository.save(CategoryMapper.toEntity(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(201, "Catégorie créée", req.getRequestURI(), CategoryMapper.toDto(saved))
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO dto,
            HttpServletRequest req
    ) {
        CategoryEntity category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse(404, "Catégorie introuvable", req.getRequestURI(), null)
            );
        }

        category.setName(dto.name());
        CategoryEntity updated = categoryRepository.save(category);

        return ResponseEntity.ok(
                new ApiResponse(200, "Catégorie mise à jour", req.getRequestURI(), CategoryMapper.toDto(updated))
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(
            @PathVariable Long id,
            HttpServletRequest req
    ) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.status(404).body(
                    new ApiResponse(404, "Catégorie introuvable", req.getRequestURI(), null)
            );
        }

        categoryRepository.deleteById(id);

        return ResponseEntity.status(204).body(
                new ApiResponse(204, "Catégorie supprimée", req.getRequestURI(), null)
        );
    }
}
