package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.CustomerService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerRequestDTO;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getAll(HttpServletRequest http) {
        List<CustomerResponseDTO> customers = service.getAll();
        ApiResponse<List<CustomerResponseDTO>> res =
                new ApiResponse<>(HttpStatus.OK.value(), "Liste des clients récupérée", http.getRequestURI(), customers);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getById(
            @PathVariable Long id,
            HttpServletRequest http
    ) {
        CustomerResponseDTO customer = service.getById(id);
        ApiResponse<CustomerResponseDTO> res =
                new ApiResponse<>(HttpStatus.OK.value(), "Client trouvé", http.getRequestURI(), customer);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> create(
            @RequestBody CustomerRequestDTO dto,
            HttpServletRequest http
    ) {
        CustomerResponseDTO created = service.create(dto);
        ApiResponse<CustomerResponseDTO> res =
                new ApiResponse<>(HttpStatus.CREATED.value(), "Client créé", http.getRequestURI(), created);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody CustomerRequestDTO dto,
            HttpServletRequest http
    ) {
        CustomerResponseDTO updated = service.update(id, dto);
        ApiResponse<CustomerResponseDTO> res =
                new ApiResponse<>(HttpStatus.OK.value(), "Client mis à jour", http.getRequestURI(), updated);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest http
    ) {
        service.delete(id);
        ApiResponse<Void> res =
                new ApiResponse<>(HttpStatus.OK.value(), "Client supprimé", http.getRequestURI(), null);
        return ResponseEntity.ok(res);
    }
}
