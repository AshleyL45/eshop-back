package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.CareInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareInfoRepository extends JpaRepository<CareInfoEntity, Long> {
}
