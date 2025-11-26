package com.greta.eshop_api.exposition.dtos.BotanicalInfo;

public record BotanicalInfoResponseDTO (
        String family,
        String origin,
        String lifespan,
        String toxicity,
        String difficulty
) {}
