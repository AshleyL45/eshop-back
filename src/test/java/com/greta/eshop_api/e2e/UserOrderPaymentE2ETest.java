package com.greta.eshop_api.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greta.eshop_api.EshopApiApplication;
import com.greta.eshop_api.persistence.entities.OrderEntity;
import com.greta.eshop_api.persistence.repositories.OrderRepository;
import com.greta.eshop_api.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EshopApiApplication.class
)
@AutoConfigureMockMvc
class UserOrderPaymentE2ETest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;
    @Autowired UserRepository userRepository;
    @Autowired OrderRepository orderRepository;

    @BeforeEach
    void cleanDb() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    private String registerAndLoginUser(String email) throws Exception {
        String registerJson = """
            {"email": "%s", "password": "secret123"}
        """.formatted(email);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isOk());

        String loginJson = """
            {"email": "%s", "password": "secret123"}
        """.formatted(email);

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode node = mapper.readTree(response);
        return node.get("token").asText();
    }

    private Long createPendingOrder(String token) {
        OrderEntity order = new OrderEntity();
        order.setStatus(com.greta.eshop_api.domain.enums.OrderStatus.PENDING);
        return orderRepository.save(order).getId();
    }

    @Test
    void shouldMarkOrderAsPaidWhenPaymentSuccess() throws Exception {
        String token = registerAndLoginUser("user1@test.com");
        Long orderId = createPendingOrder(token);

        String body = """
            {"status": "PAID"}
        """;

        mockMvc.perform(post("/payments/%d".formatted(orderId))
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        assertThat(order.getStatus()).isEqualTo(com.greta.eshop_api.domain.enums.OrderStatus.PAID);
    }

    @Test
    void shouldNotMarkOrderAsPaidWhenPaymentFails() throws Exception {
        String token = registerAndLoginUser("user2@test.com");
        Long orderId = createPendingOrder(token);

        String body = """
            {"status": "FAILED"}
        """;

        mockMvc.perform(post("/payments/%d".formatted(orderId))
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());

        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        assertThat(order.getStatus()).isEqualTo(com.greta.eshop_api.domain.enums.OrderStatus.PENDING);
    }
}
