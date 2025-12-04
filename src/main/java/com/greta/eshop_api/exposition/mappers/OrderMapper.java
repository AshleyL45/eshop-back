package com.greta.eshop_api.exposition.mappers;

import com.greta.eshop_api.exposition.dtos.Order.OrderResponseDTO;
import com.greta.eshop_api.persistence.entities.OrderEntity;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toResponseDTO(OrderEntity entity) {

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(entity.getId());
        dto.setOrderDate(entity.getOrderDate());

        dto.setCustomerId(entity.getCustomer().getId());

        dto.setCustomer(CustomerMapper.toResponseDTO(entity.getCustomer()));

        dto.setShippingAddressId(entity.getShippingAddress().getId());
        dto.setBillingAddressId(entity.getBillingAddress().getId());
        dto.setStatus(entity.getStatus().name());

        dto.setItems(
                entity.getItems().stream()
                        .map(OrderItemMapper::toResponseDTO)
                        .collect(Collectors.toList())
        );

        dto.setPayment(
                entity.getPayment() != null
                        ? PaymentMapper.toResponseDTO(entity.getPayment())
                        : null
        );

        return dto;
    }
}
