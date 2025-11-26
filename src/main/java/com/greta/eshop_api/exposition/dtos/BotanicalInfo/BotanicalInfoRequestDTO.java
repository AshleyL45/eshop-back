package com.greta.eshop_api.exposition.dtos.BotanicalInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BotanicalInfoRequestDTO (
    @NotNull String family,
    @NotBlank String origin,
    @NotBlank String lifespan,
    @NotBlank String toxicity,
    @NotBlank String difficulty
) {}
