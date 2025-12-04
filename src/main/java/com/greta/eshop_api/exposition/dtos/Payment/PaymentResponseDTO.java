package com.greta.eshop_api.exposition.dtos.Payment;

import java.time.LocalDateTime;

public class PaymentResponseDTO {

    private Long id;
    private String method;
    private Double amount;
    private LocalDateTime paymentDate;
    private Long orderId;

    public PaymentResponseDTO(Long id, String method, Double amount, LocalDateTime paymentDate, Long orderId) {
        this.id = id;
        this.method = method;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

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
