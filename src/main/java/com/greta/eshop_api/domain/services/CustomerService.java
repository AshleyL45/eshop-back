package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.exposition.dtos.Customer.CustomerRequestDTO;
import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import com.greta.eshop_api.exposition.mappers.CustomerMapper;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toResponseDTO)
                .toList();
    }

    public CustomerResponseDTO getById(Long id) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));
        return CustomerMapper.toResponseDTO(entity);
    }

    public CustomerResponseDTO create(CustomerRequestDTO dto) {
        CustomerEntity entity = CustomerMapper.toEntity(dto);
        CustomerEntity saved = repository.save(entity);
        return CustomerMapper.toResponseDTO(saved);
    }

    public CustomerResponseDTO update(Long id, CustomerRequestDTO dto) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));
        CustomerMapper.updateEntity(entity, dto);
        CustomerEntity saved = repository.save(entity);
        return CustomerMapper.toResponseDTO(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Client introuvable");
        }
        repository.deleteById(id);
    }

    public CustomerResponseDTO getByUserId(Long userId) {
        CustomerEntity entity = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun profil trouvé pour cet utilisateur"));
        return CustomerMapper.toResponseDTO(entity);
    }
}
