package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    List<FavoriteEntity> findByCustomerId(Long customerId);
    boolean existsByCustomerIdAndProductId(Long customerId, Long productId);


}
