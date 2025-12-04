package com.greta.eshop_api.exposition.mappers;


import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemResponseDTO;
import com.greta.eshop_api.persistence.entities.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItemResponseDTO toResponseDTO(OrderItemEntity entity) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setProductId(entity.getProduct().getId());
        dto.setProductName(entity.getProduct().getName());
        return dto;
    }
}
