package com.greta.eshop_api.exposition.dtos.Order;

import com.greta.eshop_api.exposition.dtos.Customer.CustomerResponseDTO;
import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemResponseDTO;
import com.greta.eshop_api.exposition.dtos.Payment.PaymentResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private LocalDateTime orderDate;

    private Long customerId;
    private CustomerResponseDTO customer;

    private Long shippingAddressId;
    private Long billingAddressId;

    private List<OrderItemResponseDTO> items;
    private PaymentResponseDTO payment;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public CustomerResponseDTO getCustomer() { return customer; }
    public void setCustomer(CustomerResponseDTO customer) { this.customer = customer; }

    public Long getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Long shippingAddressId) { this.shippingAddressId = shippingAddressId; }

    public Long getBillingAddressId() { return billingAddressId; }
    public void setBillingAddressId(Long billingAddressId) { this.billingAddressId = billingAddressId; }

    public List<OrderItemResponseDTO> getItems() { return items; }
    public void setItems(List<OrderItemResponseDTO> items) { this.items = items; }

    public PaymentResponseDTO getPayment() { return payment; }
    public void setPayment(PaymentResponseDTO payment) { this.payment = payment; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
