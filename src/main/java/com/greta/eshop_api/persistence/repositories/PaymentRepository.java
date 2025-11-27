package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
