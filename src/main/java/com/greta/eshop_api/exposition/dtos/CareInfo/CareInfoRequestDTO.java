package com.greta.eshop_api.exposition.dtos.CareInfo;

import jakarta.validation.constraints.NotNull;

public record CareInfoRequestDTO (
    @NotNull String watering,
    @NotNull String sunlight,
    @NotNull String soilType,
    @NotNull String fertilizer
) {}
