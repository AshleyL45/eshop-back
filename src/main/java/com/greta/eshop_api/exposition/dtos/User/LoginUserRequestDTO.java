package com.greta.eshop_api.exposition.dtos.User;

public record LoginUserRequestDTO(
        String email,
        String password
) {}