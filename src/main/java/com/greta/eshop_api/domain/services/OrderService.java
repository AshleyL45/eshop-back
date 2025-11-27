package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.domain.enums.OrderStatus;
import com.greta.eshop_api.domain.rules.OrderRules;
import com.greta.eshop_api.exposition.dtos.Order.OrderRequestDTO;
import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemUpdateDTO;
import com.greta.eshop_api.exposition.dtos.Payment.PaymentUpdateDTO;
import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.persistence.entities.*;
import com.greta.eshop_api.persistence.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderEntity createOrder(OrderRequestDTO dto) {

        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer introuvable"));

        AddressEntity shipping = addressRepository.findById(dto.getShippingAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Adresse livraison introuvable"));

        AddressEntity billing = addressRepository.findById(dto.getBillingAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Adresse facturation introuvable"));

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomer(customer);
        order.setShippingAddress(shipping);
        order.setBillingAddress(billing);
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        List<OrderItemEntity> items = new ArrayList<>();

        dto.getItems().forEach(itemDTO -> {
            ProductEntity product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

            OrderItemEntity item = new OrderItemEntity();
            item.setQuantity(itemDTO.getQuantity());
            item.setProduct(product);
            item.setOrder(order);

            items.add(item);
        });

        order.setItems(items);

        double total = items.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        PaymentEntity payment = new PaymentEntity();
        payment.setMethod("card");
        payment.setAmount(total);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setOrder(order);

        order.setPayment(payment);

        OrderRules.validateBeforeCreation(order);

        return orderRepository.save(order);
    }

    public OrderEntity getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getOrdersByCustomer(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getCustomer().getId().equals(customerId))
                .toList();
    }

    public OrderItemEntity updateOrderItem(Long orderId, Long itemId, OrderItemUpdateDTO dto) {
        OrderEntity order = getById(orderId);

        OrderRules.validateOrderModification(order);

        OrderItemEntity item = order.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable dans cette commande"));

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));

        item.setProduct(product);
        item.setQuantity(dto.getQuantity());

        return orderItemRepository.save(item);
    }

    public OrderEntity updatePayment(Long orderId, PaymentUpdateDTO dto) {
        OrderEntity order = getById(orderId);

        OrderRules.validatePaymentModification(order);

        PaymentEntity oldPayment = order.getPayment();

        if (oldPayment == null) {
            throw new RuntimeException("Aucun paiement n'est associé à cette commande.");
        }

        order.setPayment(null);
        orderRepository.save(order);

        PaymentEntity newPayment = new PaymentEntity();
        newPayment.setMethod(dto.getMethod());
        newPayment.setAmount(oldPayment.getAmount());
        newPayment.setPaymentDate(LocalDateTime.now());
        newPayment.setOrder(order);

        order.setPayment(newPayment);

        OrderRules.validateBeforeUpdate(order);

        return orderRepository.save(order);
    }

    public OrderEntity updateStatus(Long id, OrderStatus newStatus) {
        OrderEntity order = getById(id);

        OrderRules.validateOrderModification(order);

        order.setStatus(newStatus);

        return orderRepository.save(order);
    }

    public OrderEntity cancelOrder(Long id) {

        OrderEntity order = getById(id);

        OrderRules.validateBeforeCancellation(order);

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        OrderEntity order = getById(id);

        OrderRules.validateBeforeDeletion(order);

        orderRepository.delete(order);
    }

    public List<OrderEntity> search(String query) {
        return orderRepository.searchOrders(query);
    }

}
