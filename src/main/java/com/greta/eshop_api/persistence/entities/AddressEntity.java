package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String zipCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "shippingAddress")
    private List<OrderEntity> shippedOrders;

    @OneToMany(mappedBy = "billingAddress")
    private List<OrderEntity> billedOrders;

    public AddressEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<OrderEntity> getShippedOrders() {
        return shippedOrders;
    }

    public void setShippedOrders(List<OrderEntity> shippedOrders) {
        this.shippedOrders = shippedOrders;
    }

    public List<OrderEntity> getBilledOrders() {
        return billedOrders;
    }

    public void setBilledOrders(List<OrderEntity> billedOrders) {
        this.billedOrders = billedOrders;
    }
}
