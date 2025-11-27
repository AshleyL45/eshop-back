package com.greta.eshop_api.exposition.dtos.Order;

import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemRequestDTO;

import java.util.List;

public class OrderRequestDTO {

    private Long customerId;
    private Long shippingAddressId;
    private Long billingAddressId;

    private List<OrderItemRequestDTO> items;

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Long shippingAddressId) { this.shippingAddressId = shippingAddressId; }

    public Long getBillingAddressId() { return billingAddressId; }
    public void setBillingAddressId(Long billingAddressId) { this.billingAddressId = billingAddressId; }

    public List<OrderItemRequestDTO> getItems() { return items; }
    public void setItems(List<OrderItemRequestDTO> items) { this.items = items; }
}
