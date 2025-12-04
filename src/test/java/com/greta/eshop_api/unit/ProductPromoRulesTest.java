package com.greta.eshop_api.unit;

import com.greta.eshop_api.domain.rules.ProductPromoRules;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductPromoRulesTest {

    private ProductEntity product;

    @BeforeEach
    void setup() {
        product = new ProductEntity();
        product.setPrice(100);
        product.setActive(true);
        product.setDiscount(0);
        product.setPromoStart(LocalDate.now());
        product.setPromoEnd(LocalDate.now().plusDays(5));
    }

    @Test
    @DisplayName("shouldThrowIfDiscountAbove100")
    void shouldThrowIfDiscountAbove100() {
        product.setDiscount(150);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));
        assertTrue(ex.getMessage().contains("Invalid discount"));
    }

    @Test
    @DisplayName("shouldThrowIfDiscountNegative")
    void shouldThrowIfDiscountNegative() {
        product.setDiscount(-10);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));
        assertTrue(ex.getMessage().contains("Invalid discount"));
    }

    @Test
    @DisplayName("shouldThrowIfDiscountedPriceNegative")
    void shouldThrowIfDiscountedPriceNegative() {
        product.setPrice(-10);
        product.setDiscount(50);

        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));

        assertTrue(ex.getMessage().contains("negative"));
    }

    @Test
    @DisplayName("shouldThrowIfInactiveProductHasDiscount")
    void shouldThrowIfInactiveProductHasDiscount() {
        product.setActive(false);
        product.setDiscount(10);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));
        assertTrue(ex.getMessage().contains("Inactive product"));
    }

    @Test
    @DisplayName("shouldThrowIfPromoDatesInvalid")
    void shouldThrowIfPromoDatesInvalid() {
        product.setPromoStart(LocalDate.now().plusDays(10));
        product.setPromoEnd(LocalDate.now());
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));
        assertTrue(ex.getMessage().contains("Invalid promo dates"));
    }

    @Test
    @DisplayName("shouldThrowIfPromoExpired")
    void shouldThrowIfPromoExpired() {
        product.setDiscount(20);
        product.setPromoStart(LocalDate.now().minusDays(10)); // <-- AJOUT
        product.setPromoEnd(LocalDate.now().minusDays(1));    // promo expirée

        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductPromoRules.validate(product));

        assertTrue(ex.getMessage().contains("Expired promo"));
    }




    @Test
    @DisplayName("shouldPassWhenPromoIsValid")
    void shouldPassWhenPromoIsValid() {
        assertDoesNotThrow(() -> ProductPromoRules.validate(product));
    }
}
