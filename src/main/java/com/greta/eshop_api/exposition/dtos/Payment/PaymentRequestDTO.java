package com.greta.eshop_api.exposition.dtos.Payment;

import java.time.LocalDateTime;

public class PaymentRequestDTO {

    private String method;
    private Double amount;
    private LocalDateTime paymentDate;
    private Long orderId;

    public String getMethod() {
        return method;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Long getOrderId() {
        return orderId;
    }
}
