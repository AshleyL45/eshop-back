package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
