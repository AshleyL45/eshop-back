package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.persistence.entities.AddressEntity;

public class AddressRules {

    public static void validateBeforeCreation(AddressEntity address) {
        if (address.getStreet().length() < 3) {
            throw new RuntimeException("L'adresse est trop courte.");
        }
        if (address.getZipCode().length() < 3) {
            throw new RuntimeException("Le code postal est invalide.");
        }
    }

    public static void validateBeforeUpdate(AddressEntity address) {
        if (address.getStreet().isBlank()) {
            throw new RuntimeException("La rue ne peut pas être vide.");
        }
    }
}
