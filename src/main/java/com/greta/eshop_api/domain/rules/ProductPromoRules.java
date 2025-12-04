package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.persistence.entities.ProductEntity;

import java.time.LocalDate;

public class ProductPromoRules {

    public static void validate(ProductEntity product) {

        if (product.getDiscount() < 0 || product.getDiscount() > 100) {
            throw new RuntimeException("Invalid discount");
        }

        double discountedPrice = product.getPrice() * (1 - product.getDiscount() / 100.0);
        if (discountedPrice < 0) {
            throw new RuntimeException("Discount makes price negative");
        }

        if (!product.isActive() && product.getDiscount() > 0) {
            throw new RuntimeException("Inactive product cannot have discount");
        }

        LocalDate start = product.getPromoStart();
        LocalDate end = product.getPromoEnd();

        if (start != null && end != null && start.isAfter(end)) {
            throw new RuntimeException("Invalid promo dates");
        }

        if (product.getDiscount() > 0 && end != null && end.isBefore(LocalDate.now())) {
            throw new RuntimeException("Expired promo cannot be active");
        }
    }
}
