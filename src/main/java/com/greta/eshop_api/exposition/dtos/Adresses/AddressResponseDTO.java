package com.greta.eshop_api.exposition.dtos.Adresses;

public record AddressResponseDTO(
        Long id,
        String street,
        String city,
        String zipCode,
        String country,
        Long customerId
) {}
