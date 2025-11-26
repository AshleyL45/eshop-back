package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    boolean existsByCustomerId(Long customerId);
    CartEntity findByCustomerId(Long customerId);
}
