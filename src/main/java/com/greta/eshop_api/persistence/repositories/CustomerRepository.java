package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUserId(Long userId);

}
