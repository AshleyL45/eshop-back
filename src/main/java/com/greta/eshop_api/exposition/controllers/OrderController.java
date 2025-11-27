package com.greta.eshop_api.exposition.controllers;

import com.greta.eshop_api.domain.enums.OrderStatus;
import com.greta.eshop_api.domain.services.OrderService;
import com.greta.eshop_api.exposition.dtos.ApiResponse;
import com.greta.eshop_api.exposition.dtos.Order.OrderRequestDTO;
import com.greta.eshop_api.exposition.dtos.Order.OrderResponseDTO;
import com.greta.eshop_api.exposition.dtos.Order.OrderStatusUpdateDTO;
import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemUpdateDTO;
import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemResponseDTO;
import com.greta.eshop_api.exposition.dtos.Payment.PaymentUpdateDTO;
import com.greta.eshop_api.exposition.mappers.OrderItemMapper;
import com.greta.eshop_api.exposition.mappers.OrderMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> create(
            @RequestBody OrderRequestDTO dto,
            HttpServletRequest request
    ) {
        var order = orderService.createOrder(dto);

        return ApiResponse.created(
                "Commande créée",
                request.getRequestURI(),
                OrderMapper.toResponseDTO(order)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOne(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        var order = orderService.getById(id);

        return ApiResponse.ok(
                "Commande récupérée",
                request.getRequestURI(),
                OrderMapper.toResponseDTO(order)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getAll(
            HttpServletRequest request
    ) {
        var orders = orderService.getAll()
                .stream()
                .map(OrderMapper::toResponseDTO)
                .toList();

        return ApiResponse.ok(
                "Liste des commandes",
                request.getRequestURI(),
                orders
        );
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrdersForCustomer(
            @PathVariable Long customerId,
            HttpServletRequest request
    ) {
        var orders = orderService.getOrdersByCustomer(customerId)
                .stream()
                .map(OrderMapper::toResponseDTO)
                .toList();

        return ApiResponse.ok(
                "Commandes du client",
                request.getRequestURI(),
                orders
        );
    }

    @PutMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> updateItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId,
            @RequestBody OrderItemUpdateDTO dto,
            HttpServletRequest request
    ) {
        var updatedItem = orderService.updateOrderItem(orderId, itemId, dto);

        return ApiResponse.ok(
                "Article mis à jour",
                request.getRequestURI(),
                OrderItemMapper.toResponseDTO(updatedItem)
        );
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updatePayment(
            @PathVariable Long id,
            @RequestBody PaymentUpdateDTO dto,
            HttpServletRequest request
    ) {
        var order = orderService.updatePayment(id, dto);

        return ApiResponse.ok(
                "Paiement mis à jour",
                request.getRequestURI(),
                OrderMapper.toResponseDTO(order)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusUpdateDTO dto,
            HttpServletRequest request
    ) {
        var order = orderService.updateStatus(id, dto.getStatus());

        return ApiResponse.ok(
                "Statut mis à jour",
                request.getRequestURI(),
                OrderMapper.toResponseDTO(order)
        );
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> cancel(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        var order = orderService.cancelOrder(id);

        return ApiResponse.ok(
                "Commande annulée",
                request.getRequestURI(),
                OrderMapper.toResponseDTO(order)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOne(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        orderService.deleteOrder(id);

        return ApiResponse.ok(
                "Commande supprimée",
                request.getRequestURI(),
                null
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> search(
            @RequestParam String query,
            HttpServletRequest request
    ) {
        var orders = orderService.search(query)
                .stream()
                .map(OrderMapper::toResponseDTO)
                .toList();

        return ApiResponse.ok(
                "Résultats de la recherche",
                request.getRequestURI(),
                orders
        );
    }

}
