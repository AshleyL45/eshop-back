package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.CareInfo.CareInfoRequestDTO;
import com.greta.eshop_api.exposition.dtos.CareInfo.CareInfoResponseDTO;
import com.greta.eshop_api.persistence.entities.CareInfoEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;

public class CareInfoMapper {

    public static CareInfoEntity toEntity(CareInfoRequestDTO dto, ProductEntity product) {
        CareInfoEntity entity = new CareInfoEntity();
        entity.setWatering(dto.watering());
        entity.setSunlight(dto.sunlight());
        entity.setSoilType(dto.soilType());
        entity.setFertilizer(dto.fertilizer());

        return entity;
    }

    public static CareInfoResponseDTO toResponseDTO(CareInfoEntity entity) {
        return new CareInfoResponseDTO(
                entity.getWatering(),
                entity.getSunlight(),
                entity.getSoilType(),
                entity.getFertilizer()
        );
    }
}
