package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.domain.enums.OrderStatus;
import com.greta.eshop_api.persistence.entities.OrderEntity;

public class OrderRules {

    public static void validateBeforeCreation(OrderEntity order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("La commande doit contenir au moins un article.");
        }
        if (order.getCustomer() == null) {
            throw new RuntimeException("La commande doit être associée à un client.");
        }
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Lors de sa création, une commande doit être en statut PENDING_PAYMENT.");
        }
    }

    public static void validateOrderModification(OrderEntity order) {
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Impossible de modifier une commande annulée.");
        }
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Impossible de modifier une commande déjà expédiée ou livrée.");
        }
    }

    public static void validatePaymentModification(OrderEntity order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Le paiement ne peut être modifié que si la commande est en attente de paiement.");
        }
    }

    public static void validateBeforeUpdate(OrderEntity order) {
        // même logique que validatePaymentModification, mais tu veux ta méthode
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Impossible de mettre à jour le paiement d'une commande expédiée ou livrée.");
        }
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Impossible de mettre à jour une commande annulée.");
        }
    }

    public static void validateBeforeCancellation(OrderEntity order) {
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Impossible d'annuler une commande expédiée ou livrée.");
        }
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Cette commande est déjà annulée.");
        }
    }

    public static void validateBeforeDeletion(OrderEntity order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT &&
                order.getStatus() != OrderStatus.CANCELLED) {
            throw new RuntimeException("Impossible de supprimer une commande déjà payée ou traitée.");
        }
    }
}
