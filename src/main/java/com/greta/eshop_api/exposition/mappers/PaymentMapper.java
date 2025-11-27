package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Payment.PaymentRequestDTO;
import com.greta.eshop_api.exposition.dtos.Payment.PaymentResponseDTO;
import com.greta.eshop_api.persistence.entities.OrderEntity;
import com.greta.eshop_api.persistence.entities.PaymentEntity;

public class PaymentMapper {

    public static PaymentEntity toEntity(PaymentRequestDTO dto, OrderEntity order) {
        PaymentEntity entity = new PaymentEntity();
        entity.setMethod(dto.getMethod());
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setOrder(order);
        return entity;
    }

    public static PaymentResponseDTO toResponseDTO(PaymentEntity entity) {
        return new PaymentResponseDTO(
                entity.getId(),
                entity.getMethod(),
                entity.getAmount(),
                entity.getPaymentDate(),
                entity.getOrder() != null ? entity.getOrder().getId() : null
        );
    }
}
