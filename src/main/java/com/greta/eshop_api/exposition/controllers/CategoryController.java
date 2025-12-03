package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.CategoryService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryRequestDTO;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAll(HttpServletRequest req) {
        List<CategoryResponseDTO> dtos = service.findAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Liste des catégories",
                        req.getRequestURI(),
                        dtos
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getById(
            @PathVariable Long id,
            HttpServletRequest req
    ) {
        CategoryResponseDTO dto = service.findById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Catégorie trouvée",
                        req.getRequestURI(),
                        dto
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(
            @Valid @RequestBody CategoryRequestDTO dto,
            HttpServletRequest req
    ) {
        CategoryResponseDTO created = service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Catégorie créée",
                        req.getRequestURI(),
                        created
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO dto,
            HttpServletRequest req
    ) {
        CategoryResponseDTO updated = service.update(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Catégorie mise à jour",
                        req.getRequestURI(),
                        updated
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest req
    ) {
        service.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Catégorie supprimée",
                        req.getRequestURI(),
                        null
                )
        );
    }
}
