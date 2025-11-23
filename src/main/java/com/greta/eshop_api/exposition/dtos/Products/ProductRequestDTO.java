package com.greta.eshop_api.exposition.dtos.Products;

import jakarta.validation.constraints.*;

public record ProductRequestDTO(

        @NotBlank(message = "Le nom du produit ne peut pas être vide")
        @Size(max = 100, message = "Le nom du produit ne doit pas dépasser 100 caractères")
        String name,

        @NotBlank(message = "Le nom scientifique ne peut pas être vide")
        @Size(max = 150, message = "Le nom scientifique ne doit pas dépasser 150 caractères")
        String scientificName,

        @NotBlank(message = "La description ne peut pas être vide")
        @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
        String description,

        @Size(max = 2000, message = "La description longue ne doit pas dépasser 2000 caractères")
        String longDescription,

        @NotNull(message = "Le prix ne peut pas être null")
        @Positive(message = "Le prix doit être positif")
        @Min(value = 1, message = "Le prix doit être au moins de 1 euro")
        @Max(value = 10000, message = "Le prix ne peut pas dépasser 10 000 euros")
        double price,

        @NotBlank(message = "L'URL de l’image ne peut pas être vide")
        @Size(max = 255, message = "L'URL de l’image ne doit pas dépasser 255 caractères")
        @Pattern(
                regexp = "^(https?://).+$",
                message = "L'URL de l’image doit commencer par http:// ou https://"
        )
        String imageUrl,

        @PositiveOrZero(message = "La quantité en stock ne peut pas être négative")
        int stockQuantity,

        @PositiveOrZero(message = "La note doit être >= 0")
        @Max(value = 5, message = "La note ne peut pas dépasser 5")
        double rating,

        @NotNull(message = "Le statut actif ne peut pas être null")
        boolean active,

        @PositiveOrZero(message = "La remise ne peut pas être négative")
        @Max(value = 90, message = "La remise ne peut pas dépasser 90%")
        double discount,

        @Size(max = 1000, message = "Le conseil d'expert ne doit pas dépasser 1000 caractères")
        String expertAdvice,

        @NotNull(message = "L'ID de la catégorie est obligatoire")
        Long categoryId

) {}
