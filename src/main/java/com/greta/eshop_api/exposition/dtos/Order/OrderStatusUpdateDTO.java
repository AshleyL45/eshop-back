package com.greta.eshop_api.exposition.dtos.Order;

import com.greta.eshop_api.domain.enums.OrderStatus;

public class OrderStatusUpdateDTO {

    private OrderStatus status;

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
