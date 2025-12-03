package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.services.CustomerService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerRequestDTO;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import com.greta.eshop_api.persistence.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Liste des clients récupérée",
                        http.getRequestURI(),
                        customers
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getById(
            @PathVariable Long id,
            HttpServletRequest http
    ) {
        CustomerResponseDTO customer = service.getById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Client trouvé",
                        http.getRequestURI(),
                        customer
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> create(
            @Valid @RequestBody CustomerRequestDTO dto,
            HttpServletRequest http
    ) {
        CustomerResponseDTO created = service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "Client créé",
                        http.getRequestURI(),
                        created
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO dto,
            HttpServletRequest http
    ) {
        CustomerResponseDTO updated = service.update(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Client mis à jour",
                        http.getRequestURI(),
                        updated
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest http
    ) {
        service.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Client supprimé",
                        http.getRequestURI(),
                        null
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getMyProfile(
            @AuthenticationPrincipal UserEntity user,
            HttpServletRequest http
    ) {
        CustomerResponseDTO dto = service.getByUserId(user.getId());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Profil récupéré",
                        http.getRequestURI(),
                        dto
                )
        );
    }

}
