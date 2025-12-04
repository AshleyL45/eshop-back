package com.greta.eshop_api.exposition.mappers;


import com.greta.eshop_api.exposition.dtos.Customer.CustomerRequestDTO;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import com.greta.eshop_api.persistence.entities.CustomerEntity;

public class CustomerMapper {

    public static CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public static CustomerEntity toEntity(CustomerRequestDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public static void updateEntity(CustomerEntity entity, CustomerRequestDTO dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
    }
}
