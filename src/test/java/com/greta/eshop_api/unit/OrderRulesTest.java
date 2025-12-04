package com.greta.eshop_api.unit;

//mvn test -Punit-tests

import com.greta.eshop_api.domain.enums.OrderStatus;
import com.greta.eshop_api.domain.rules.OrderRules;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.OrderEntity;
import com.greta.eshop_api.persistence.entities.OrderItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRulesTest {

    private OrderEntity order;

    @BeforeEach
    void setup() {
        order = new OrderEntity();
        order.setCustomer(new CustomerEntity());
        order.setItems(List.of(new OrderItemEntity()));
        order.setStatus(OrderStatus.PENDING_PAYMENT);
    }

    @Test
    @DisplayName("Should throw if order has no items")
    void shouldThrowIfOrderHasNoItems() {
        order.setItems(List.of());
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeCreation(order));
        assertTrue(ex.getMessage().contains("au moins un article"));
    }

    @Test
    @DisplayName("Should throw if order has no customer")
    void shouldThrowIfOrderHasNoCustomer() {
        order.setCustomer(null);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeCreation(order));
        assertTrue(ex.getMessage().contains("associée à un client"));
    }

    @Test
    @DisplayName("Should throw if order creation status is invalid")
    void shouldThrowIfOrderCreatedWithInvalidStatus() {
        order.setStatus(OrderStatus.SHIPPED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeCreation(order));
        assertTrue(ex.getMessage().contains("PENDING_PAYMENT"));
    }

    @Test
    @DisplayName("Should pass when order creation is valid")
    void shouldPassOnValidOrderCreation() {
        assertDoesNotThrow(() -> OrderRules.validateBeforeCreation(order));
    }

    @Test
    @DisplayName("Should throw when modifying cancelled order")
    void shouldThrowWhenModifyingCancelledOrder() {
        order.setStatus(OrderStatus.CANCELLED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateOrderModification(order));
        assertTrue(ex.getMessage().contains("annulée"));
    }

    @Test
    @DisplayName("Should throw when modifying shipped or delivered order")
    void shouldThrowWhenModifyingShippedOrDelivered() {
        order.setStatus(OrderStatus.DELIVERED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateOrderModification(order));
        assertTrue(ex.getMessage().contains("expédiée ou livrée"));
    }

    @Test
    @DisplayName("Should pass when modification is allowed")
    void shouldPassOnValidModification() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        assertDoesNotThrow(() -> OrderRules.validateOrderModification(order));
    }

    @Test
    @DisplayName("Should throw if payment update is invalid")
    void shouldThrowWhenUpdatingPaymentInvalidStatus() {
        order.setStatus(OrderStatus.SHIPPED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validatePaymentModification(order));
        assertTrue(ex.getMessage().contains("en attente de paiement"));
    }

    @Test
    @DisplayName("Should pass if payment modification is allowed")
    void shouldPassOnValidPaymentModification() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        assertDoesNotThrow(() -> OrderRules.validatePaymentModification(order));
    }

    @Test
    @DisplayName("Should throw when updating shipped or delivered order")
    void shouldThrowOnUpdateShippedOrDelivered() {
        order.setStatus(OrderStatus.SHIPPED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeUpdate(order));
        assertTrue(ex.getMessage().contains("expédiée ou livrée"));
    }

    @Test
    @DisplayName("Should throw when updating cancelled order")
    void shouldThrowOnUpdateCancelledOrder() {
        order.setStatus(OrderStatus.CANCELLED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeUpdate(order));
        assertTrue(ex.getMessage().contains("annulée"));
    }

    @Test
    @DisplayName("Should pass when update is allowed")
    void shouldPassOnValidUpdate() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        assertDoesNotThrow(() -> OrderRules.validateBeforeUpdate(order));
    }

    @Test
    @DisplayName("Should throw when cancelling shipped or delivered order")
    void shouldThrowOnCancelShippedOrDelivered() {
        order.setStatus(OrderStatus.SHIPPED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeCancellation(order));
        assertTrue(ex.getMessage().contains("expédiée ou livrée"));
    }

    @Test
    @DisplayName("Should throw when cancelling already cancelled order")
    void shouldThrowOnCancelAlreadyCancelled() {
        order.setStatus(OrderStatus.CANCELLED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeCancellation(order));
        assertTrue(ex.getMessage().contains("déjà annulée"));
    }

    @Test
    @DisplayName("Should pass when cancellation is allowed")
    void shouldPassOnValidCancellation() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        assertDoesNotThrow(() -> OrderRules.validateBeforeCancellation(order));
    }

    @Test
    @DisplayName("Should throw when deleting processed order")
    void shouldThrowOnDeleteProcessedOrder() {
        order.setStatus(OrderStatus.SHIPPED);
        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRules.validateBeforeDeletion(order));
        assertTrue(ex.getMessage().contains("déjà payée ou traitée"));
    }

    @Test
    @DisplayName("Should pass when deletion is allowed")
    void shouldPassOnValidDeletion() {
        order.setStatus(OrderStatus.CANCELLED);
        assertDoesNotThrow(() -> OrderRules.validateBeforeDeletion(order));
    }
}
