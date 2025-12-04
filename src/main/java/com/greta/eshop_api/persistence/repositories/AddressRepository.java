package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
