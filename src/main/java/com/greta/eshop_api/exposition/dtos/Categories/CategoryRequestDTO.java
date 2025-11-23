package com.greta.eshop_api.exposition.dtos.Categories;

import jakarta.validation.constraints.*;

public record CategoryRequestDTO(

        @NotBlank(message = "Le nom de la catégorie ne peut pas être vide")
        @Size(max = 75, message = "Le nom de la catégorie ne doit pas dépasser 75 caractères")
        String name

) {}
