package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;

public class CustomerRules {

    public static CustomerEntity mustExist(CustomerRepository repo, Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer avec l'ID : " + id + " n'existe pas"));
    }
}
