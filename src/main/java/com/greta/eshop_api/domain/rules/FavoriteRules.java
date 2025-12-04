package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;

public class FavoriteRules {

    public static CustomerEntity mustHaveValidCustomer(CustomerRepository repo, Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer avec l'ID : " + id + " n'existe pas"));
    }

    public static ProductEntity mustHaveValidProduct(ProductRepository repo, Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product avec l'ID : " + id + " n'existe pas"));
    }
}
