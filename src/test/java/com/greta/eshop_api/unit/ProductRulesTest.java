package com.greta.eshop_api.unit;

import com.greta.eshop_api.domain.rules.ProductRules;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRulesTest {

    private ProductEntity product;

    @BeforeEach
    void setup() {
        product = new ProductEntity();
        product.setPrice(20.0);
        product.setStockQuantity(10);
    }

    @Test
    @DisplayName("Should throw if price <= 0 during creation")
    void shouldThrowIfPriceInvalidOnCreation() {
        product.setPrice(0);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductRules.validateBeforeCreation(product));
        assertTrue(ex.getMessage().contains("prix doit être supérieur"));
    }

    @Test
    @DisplayName("Should throw if stock is negative during creation")
    void shouldThrowIfStockNegativeOnCreation() {
        product.setStockQuantity(-5);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductRules.validateBeforeCreation(product));
        assertTrue(ex.getMessage().contains("stock ne peut pas être négatif"));
    }

    @Test
    @DisplayName("Should pass when creation is valid")
    void shouldPassOnValidCreation() {
        assertDoesNotThrow(() -> ProductRules.validateBeforeCreation(product));
    }

    @Test
    @DisplayName("Should throw if price exceeds limit during update")
    void shouldThrowIfPriceTooHighOnUpdate() {
        product.setPrice(15000);
        Exception ex = assertThrows(RuntimeException.class,
                () -> ProductRules.validateBeforeUpdate(product));
        assertTrue(ex.getMessage().contains("dépasse la limite"));
    }

    @Test
    @DisplayName("Should pass when update is valid")
    void shouldPassOnValidUpdate() {
        product.setPrice(9999);
        assertDoesNotThrow(() -> ProductRules.validateBeforeUpdate(product));
    }
}
