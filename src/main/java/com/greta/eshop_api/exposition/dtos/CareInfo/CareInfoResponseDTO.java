package com.greta.eshop_api.exposition.dtos.CareInfo;

public record CareInfoResponseDTO (
   String watering,
   String sunlight,
   String soilType,
   String fertilizer
) {}