package com.greta.eshop_api.domain.rules;

import com.greta.eshop_api.persistence.entities.CartEntity;
import com.greta.eshop_api.persistence.entities.CartItemEntity;

public class CartRules {

    public static void validateBeforeCartCreation(CartEntity cart) {
        if (cart.getCustomer() == null) {
            throw new RuntimeException("Un panier doit être associé à un client.");
        }
    }

    public static void validateCartItem(CartItemEntity item) {
        if (item.getQuantity() <= 0) {
            throw new RuntimeException("La quantité d'un article doit être supérieure ou égale à 1.");
        }
        if (item.getProduct() == null) {
            throw new RuntimeException("Un article doit contenir un produit.");
        }
    }

    public static void validateBeforeAddingItem(CartEntity cart, CartItemEntity item) {
        if (cart == null) {
            throw new RuntimeException("Le panier n'existe pas.");
        }
        validateCartItem(item);
    }
}
