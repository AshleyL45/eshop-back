package com.greta.eshop_api.integration;

import com.greta.eshop_api.domain.services.OrderService;
import com.greta.eshop_api.exposition.dtos.Order.OrderRequestDTO;
import com.greta.eshop_api.exposition.dtos.OrderItem.OrderItemRequestDTO;
import com.greta.eshop_api.persistence.entities.*;
import com.greta.eshop_api.persistence.repositories.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("integration")
class OrderServiceIntegrationTest {

    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private AddressRepository addressRepository;

    @Test
    @Transactional
    void shouldCreateOrderAndPersistItWithItemsAndPayment() {


        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@doe.com");
        customer = customerRepository.save(customer);

        AddressEntity shipping = new AddressEntity();
        shipping.setStreet("123 road");
        shipping.setCity("Paris");
        shipping.setCountry("France");
        shipping.setZipCode("75000");
        shipping = addressRepository.save(shipping);

        AddressEntity billing = new AddressEntity();
        billing.setStreet("321 avenue");
        billing.setCity("Paris");
        billing.setCountry("France");
        billing.setZipCode("75001");
        billing = addressRepository.save(billing);

        ProductEntity product = new ProductEntity();
        product.setName("Aloe Vera");
        product.setScientificName("Aloe barbadensis");
        product.setDescription("Green plant");
        product.setLongDescription("Very good plant");
        product.setPrice(19.99);
        product.setImageUrl("aloe.png");
        product.setStockQuantity(50);
        product.setRating(4.5);

        product.setExpertAdvice("Basic care instructions");
        product.setActive(true);
        product.setDiscount(0.0);

        product.setPromoStart(LocalDate.now());
        product.setPromoEnd(LocalDate.now().plusDays(30));

        CareInfoEntity care = new CareInfoEntity();
        care.setWatering("moderate");
        care.setSunlight("bright indirect light");
        care.setSoilType("well-drained");
        care.setFertilizer("monthly");

        BotanicalInfoEntity bio = new BotanicalInfoEntity();
        bio.setFamily("Asphodelaceae");
        bio.setOrigin("North Africa");
        bio.setLifespan("perennial");
        bio.setToxicity("non-toxic");
        bio.setDifficulty("easy");

        product.setCareInfo(care);
        product.setBotanicalInfo(bio);

        product = productRepository.save(product);




        OrderItemRequestDTO itemDTO =
                new OrderItemRequestDTO(product.getId(), 2);

        OrderRequestDTO dto = new OrderRequestDTO(
                customer.getId(),
                shipping.getId(),
                billing.getId(),
                List.of(itemDTO)
        );


        OrderEntity saved = orderService.createOrder(dto);

        List<OrderEntity> allOrders = orderRepository.findAll();

        assertThat(allOrders).hasSize(1);

        OrderEntity persisted = allOrders.get(0);

        assertThat(persisted.getCustomer().getFirstName()).isEqualTo("John");

        assertThat(persisted.getItems()).hasSize(1);
        OrderItemEntity persistedItem = persisted.getItems().get(0);
        assertThat(persistedItem.getProduct().getName()).isEqualTo("Aloe Vera");
        assertThat(persistedItem.getQuantity()).isEqualTo(2);

        assertThat(persisted.getPayment()).isNotNull();
        assertThat(persisted.getPayment().getAmount()).isEqualTo(19.99 * 2);
        assertThat(persisted.getPayment().getMethod()).isEqualTo("card");
    }
}
