package com.greta.eshop_api.exposition.mappers;


import com.greta.eshop_api.exposition.dtos.Adresses.AddressRequestDTO;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressResponseDTO;
import com.greta.eshop_api.persistence.entities.AddressEntity;
import com.greta.eshop_api.persistence.entities.CustomerEntity;

public class AddressMapper {

    public static AddressEntity toEntity(AddressRequestDTO dto, CustomerEntity customer) {

        AddressEntity entity = new AddressEntity();
        entity.setStreet(dto.street());
        entity.setCity(dto.city());
        entity.setZipCode(dto.zipCode());
        entity.setCountry(dto.country());
        entity.setCustomer(customer);

        return entity;
    }

    public static AddressResponseDTO toResponseDTO(AddressEntity entity) {
        return new AddressResponseDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getZipCode(),
                entity.getCountry(),
                entity.getCustomer() != null ? entity.getCustomer().getId() : null
        );
    }
}
