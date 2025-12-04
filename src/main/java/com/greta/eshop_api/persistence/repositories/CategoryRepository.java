package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
