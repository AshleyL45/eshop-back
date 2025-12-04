package com.greta.eshop_api.exposition.dtos.Adresses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequestDTO(
        @NotBlank String street,
        @NotBlank String city,
        @NotBlank String zipCode,
        @NotBlank String country,
        @NotNull Long customerId
) {}
