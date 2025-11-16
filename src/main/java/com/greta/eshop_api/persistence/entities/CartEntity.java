package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity> items;

    public CartEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<CartItemEntity> getItems() {
        return items;
    }

    public void setItems(List<CartItemEntity> items) {
        this.items = items;
    }
}
