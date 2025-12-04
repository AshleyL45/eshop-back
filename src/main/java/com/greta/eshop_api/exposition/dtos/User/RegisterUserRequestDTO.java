package com.greta.eshop_api.exposition.dtos.User;

import com.greta.eshop_api.domain.enums.Role;
import com.greta.eshop_api.persistence.entities.UserEntity;

public record RegisterUserRequestDTO(
        String email,
        String password
) {
    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setRole(Role.ROLE_USER);

        return user;
    }
}