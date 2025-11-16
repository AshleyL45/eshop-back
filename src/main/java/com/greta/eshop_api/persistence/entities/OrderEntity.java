package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private AddressEntity shippingAddress;

    @ManyToOne
    @JoinColumn(name = "billing_address_id")
    private AddressEntity billingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentEntity payment;

    public OrderEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public AddressEntity getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressEntity shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AddressEntity getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressEntity billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
