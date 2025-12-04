package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
