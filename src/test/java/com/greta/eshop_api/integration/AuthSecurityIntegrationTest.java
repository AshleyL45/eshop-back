package com.greta.eshop_api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greta.eshop_api.domain.enums.Role;
import com.greta.eshop_api.exposition.dtos.User.LoginUserRequestDTO;
import com.greta.eshop_api.persistence.entities.UserEntity;
import com.greta.eshop_api.persistence.repositories.UserRepository;
import com.greta.eshop_api.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class AuthSecurityIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        userRepository.save(new UserEntity(
                "user@test.com",
                passwordEncoder.encode("password"),
                Role.ROLE_USER
        ));

        userRepository.save(new UserEntity(
                "admin@test.com",
                passwordEncoder.encode("adminpass"),
                Role.ROLE_ADMIN
        ));
    }

    private SecretKey extractSigningKey() {
        try {
            Field f = JwtUtil.class.getDeclaredField("key");
            f.setAccessible(true);
            return (SecretKey) f.get(jwtUtil);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String expiredTokenFor(UserEntity user) {
        SecretKey key = extractSigningKey();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis() - 999999))
                .setExpiration(new Date(System.currentTimeMillis() - 5000)) // expiré
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void loginShouldReturnToken() throws Exception {
        LoginUserRequestDTO login = new LoginUserRequestDTO("user@test.com", "password");

        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.email").value("user@test.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    void loginInvalidShouldReturn401() throws Exception {
        LoginUserRequestDTO login = new LoginUserRequestDTO("user@test.com", "wrongpass");

        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void expiredTokenShouldReturn401() throws Exception {
        UserEntity user = userRepository.findByEmail("user@test.com").get();
        String expired = expiredTokenFor(user);

        mockMvc.perform(get("/orders") // route protégée
                        .header("Authorization", "Bearer " + expired))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userAccessAdminShouldReturn403() throws Exception {
        UserEntity user = userRepository.findByEmail("user@test.com").get();
        String token = jwtUtil.generateToken(user);

        mockMvc.perform(get("/admin/products")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden()); // 403
    }

    @Test
    void adminAccessAdminShouldReturn404() throws Exception {
        UserEntity admin = userRepository.findByEmail("admin@test.com").get();
        String token = jwtUtil.generateToken(admin);

        mockMvc.perform(get("/admin/products")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound()); // 404
    }

    @Test
    void publicRouteShouldReturn200() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}
