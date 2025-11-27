package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.rules.CustomerRules;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerRequestDTO;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import com.greta.eshop_api.exposition.mappers.CustomerMapper;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<CustomerResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(CustomerMapper::toResponseDTO)
                .toList();
    }

    public CustomerResponseDTO getById(Long id) {
        CustomerEntity entity = CustomerRules.mustExist(repo, id);
        return CustomerMapper.toResponseDTO(entity);
    }

    public CustomerResponseDTO create(CustomerRequestDTO dto) {
        CustomerEntity entity = CustomerMapper.toEntity(dto);
        repo.save(entity);
        return CustomerMapper.toResponseDTO(entity);
    }

    public CustomerResponseDTO update(Long id, CustomerRequestDTO dto) {
        CustomerEntity entity = CustomerRules.mustExist(repo, id);
        CustomerMapper.updateEntity(entity, dto);
        repo.save(entity);
        return CustomerMapper.toResponseDTO(entity);
    }

    public void delete(Long id) {
        CustomerEntity entity = CustomerRules.mustExist(repo, id);
        repo.delete(entity);
    }
}
