package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.rules.CartRules;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.exposition.dtos.Cart.CartRequestDTO;
import com.greta.eshop_api.exposition.dtos.Cart.CartResponseDTO;
import com.greta.eshop_api.exposition.dtos.CartItem.CartItemRequestDTO;
import com.greta.eshop_api.exposition.mappers.CartMapper;
import com.greta.eshop_api.persistence.entities.CartEntity;
import com.greta.eshop_api.persistence.entities.CartItemEntity;
import com.greta.eshop_api.persistence.entities.CustomerEntity;
import com.greta.eshop_api.persistence.entities.ProductEntity;
import com.greta.eshop_api.persistence.repositories.CartItemRepository;
import com.greta.eshop_api.persistence.repositories.CartRepository;
import com.greta.eshop_api.persistence.repositories.CustomerRepository;
import com.greta.eshop_api.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            CartItemRepository cartItemRepository
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public CartResponseDTO createCart(CartRequestDTO request) {

        CustomerEntity customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer avec ID " + request.customerId() + " introuvable"
                ));

        CartEntity existingCart = cartRepository.findByCustomerId(request.customerId());
        if (existingCart != null) {
            return CartMapper.toResponseDTO(existingCart);
        }

        CartEntity cart = new CartEntity();
        cart.setCustomer(customer);
        cart.setItems(new ArrayList<>());

        CartRules.validateBeforeCartCreation(cart);

        CartEntity savedCart = cartRepository.save(cart);

        List<CartItemEntity> cartItems = new ArrayList<>();

        for (CartItemRequestDTO itemDTO : request.items()) {

            ProductEntity product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produit avec ID " + itemDTO.productId() + " introuvable"
                    ));

            CartItemEntity item = new CartItemEntity();
            item.setCart(savedCart);
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity());

            CartRules.validateCartItem(item);

            cartItemRepository.save(item);
            cartItems.add(item);
        }

        savedCart.setItems(cartItems);

        return CartMapper.toResponseDTO(savedCart);
    }

    public CartResponseDTO getCartByCustomerId(Long customerId) {

        CartEntity cart = cartRepository.findByCustomerId(customerId);

        if (cart == null) {
            throw new ResourceNotFoundException(
                    "Cart pour customer " + customerId + " introuvable"
            );
        }

        return CartMapper.toResponseDTO(cart);
    }

    public CartResponseDTO updateItem(Long itemId, CartItemRequestDTO request) {

        CartItemEntity item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CartItem ID " + itemId + " introuvable"
                ));

        item.setQuantity(request.quantity());
        CartRules.validateCartItem(item);

        cartItemRepository.save(item);

        return CartMapper.toResponseDTO(item.getCart());
    }


    public void deleteItem(Long itemId) {
        CartItemEntity item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CartItem ID " + itemId + " introuvable"
                ));

        cartItemRepository.delete(item);
    }

}
