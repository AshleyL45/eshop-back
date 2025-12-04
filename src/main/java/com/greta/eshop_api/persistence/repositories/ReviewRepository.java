package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByProductId(Long productId);
}
