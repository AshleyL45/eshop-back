package com.greta.eshop_api.persistence.repositories;

import com.greta.eshop_api.persistence.entities.BotanicalInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotanicalInfoRepository extends JpaRepository<BotanicalInfoEntity, Long> {
}
