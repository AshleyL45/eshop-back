package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.AddressService;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressRequestDTO;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressResponseDTO;
import com.greta.eshop_api.exposition.dtos.ApiResponse;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> getAll(HttpServletRequest request) {
        List<AddressResponseDTO> dtos = service.findAll();
        ApiResponse resp = new ApiResponse(200, "Liste des adresses", request.getRequestURI(), dtos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id, HttpServletRequest request) {
        AddressResponseDTO dto = service.findById(id);
        ApiResponse resp = new ApiResponse(200, "Adresse récupérée", request.getRequestURI(), dto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @Valid @RequestBody AddressRequestDTO dto,
            HttpServletRequest request
    ) {
        AddressResponseDTO created = service.create(dto);
        ApiResponse resp = new ApiResponse(201, "Adresse créée", request.getRequestURI(), created);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AddressRequestDTO dto,
            HttpServletRequest request
    ) {
        AddressResponseDTO updated = service.update(id, dto);
        ApiResponse resp = new ApiResponse(200, "Adresse mise à jour", request.getRequestURI(), updated);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id, HttpServletRequest request) {
        service.delete(id);
        ApiResponse resp = new ApiResponse(204, "Adresse supprimée", request.getRequestURI(), null);
        return ResponseEntity.status(204).body(resp);
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse> deleteAll(HttpServletRequest request) {
        service.deleteAll();
        ApiResponse resp = new ApiResponse(204, "Toutes les adresses supprimées", request.getRequestURI(), null);
        return ResponseEntity.status(204).body(resp);
    }
}
