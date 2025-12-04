package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.rules.AddressRules;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressRequestDTO;
import com.greta.eshop_api.exposition.dtos.Adresses.AddressResponseDTO;
import com.greta.eshop_api.exposition.mappers.AddressMapper;
import com.greta.eshop_api.persistence.entities.AddressEntity;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.repositories.AddressRepository;

import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository,
                          CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public List<AddressResponseDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toResponseDTO)
                .toList();
    }

    public AddressResponseDTO findById(Long id) {
        AddressEntity entity = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse introuvable"));
        return AddressMapper.toResponseDTO(entity);
    }

    public AddressResponseDTO create(AddressRequestDTO dto) {

        CustomerEntity customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        AddressEntity entity = AddressMapper.toEntity(dto, customer);

        AddressRules.validateBeforeCreation(entity);

        AddressEntity saved = addressRepository.save(entity);
        return AddressMapper.toResponseDTO(saved);
    }

    public AddressResponseDTO update(Long id, AddressRequestDTO dto) {
        AddressEntity existing = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse introuvable"));

        CustomerEntity customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable"));

        existing.setStreet(dto.street());
        existing.setCity(dto.city());
        existing.setZipCode(dto.zipCode());
        existing.setCountry(dto.country());
        existing.setCustomer(customer);

        AddressRules.validateBeforeUpdate(existing);

        AddressEntity saved = addressRepository.save(existing);
        return AddressMapper.toResponseDTO(saved);
    }

    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adresse introuvable");
        }
        addressRepository.deleteById(id);
    }

    public void deleteAll() {
        addressRepository.deleteAll();
    }
}
