package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.BotanicalInfo.BotanicalInfoRequestDTO;
import com.greta.eshop_api.exposition.dtos.BotanicalInfo.BotanicalInfoResponseDTO;
import com.greta.eshop_api.persistence.entities.BotanicalInfoEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;

public class BotanicalInfoMapper {

    public static BotanicalInfoEntity toEntity(BotanicalInfoRequestDTO dto, ProductEntity product) {

        BotanicalInfoEntity entity = new BotanicalInfoEntity();
        entity.setFamily(dto.family());
        entity.setOrigin(dto.origin());
        entity.setLifespan(dto.lifespan());
        entity.setToxicity(dto.toxicity());
        entity.setDifficulty(dto.difficulty());

        return entity;
    }

    public static BotanicalInfoResponseDTO toResponseDTO(BotanicalInfoEntity entity) {
        return new BotanicalInfoResponseDTO(
                entity.getFamily(),
                entity.getOrigin(),
                entity.getLifespan(),
                entity.getToxicity(),
                entity.getDifficulty()
        );
    }
}


