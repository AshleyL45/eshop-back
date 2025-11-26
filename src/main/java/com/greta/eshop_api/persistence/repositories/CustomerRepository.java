package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
