package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.AddressService;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressRequestDTO;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressResponseDTO;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressResponseDTO>>> getAll(HttpServletRequest request) {
        List<AddressResponseDTO> dtos = service.findAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Liste des adresses",
                        request.getRequestURI(),
                        dtos
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponseDTO>> getById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        AddressResponseDTO dto = service.findById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Adresse récupérée",
                        request.getRequestURI(),
                        dto
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponseDTO>> create(
            @Valid @RequestBody AddressRequestDTO dto,
            HttpServletRequest request
    ) {
        AddressResponseDTO created = service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Adresse créée",
                        request.getRequestURI(),
                        created
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody AddressRequestDTO dto,
            HttpServletRequest request
    ) {
        AddressResponseDTO updated = service.update(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Adresse mise à jour",
                        request.getRequestURI(),
                        updated
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        service.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Adresse supprimée",
                        request.getRequestURI(),
                        null
                )
        );
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse<Void>> deleteAll(HttpServletRequest request) {
        service.deleteAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Toutes les adresses supprimées",
                        request.getRequestURI(),
                        null
                )
        );
    }
}
